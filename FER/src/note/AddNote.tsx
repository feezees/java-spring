import { useRef, useState } from "react"
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";
import { Text } from "../ui/Text";
import { Loading } from "../root/Loading";

export const AddNote = () => {
    const textAreaRef = useRef(null);
    const [createNoteStatus, setCreateNoteStatus] = useState<'loading' | string>('');

    const handleCreateNote = () => {
        if (!textAreaRef.current) {
            return;
        }

        const { value } = textAreaRef.current as HTMLTextAreaElement;
        setCreateNoteStatus('loading');

        saxios.post('/notes', {
            noteValue: value
        }).then(() => {
            setCreateNoteStatus('Note Create success!');
            (textAreaRef.current as unknown as HTMLTextAreaElement).value = '';
        }).catch(() => {
            setCreateNoteStatus('Create Note error');
        })

    };


    return (
        <div className="mb-4">
            <textarea
                ref={textAreaRef}
                className="w-full p-3 rounded-md bg-gray-700 border border-gray-600 text-gray-200 placeholder-gray-400 focus:outline-none focus:border-blue-500 mb-2"
                rows={4}
                placeholder="Write your note here..."
            />
            {createNoteStatus && createNoteStatus !== 'loading' && <Text text={createNoteStatus} />}
            {createNoteStatus === 'loading' && <Loading />}
            <Button text="submit" onClick={handleCreateNote} />
        </div>
    )
}