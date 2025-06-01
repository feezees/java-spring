import { useRef } from "react"
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";

export const AddNote = () => {
    const textAreaRef = useRef(null);
    
    const handleCreateNote = () => {
        if (!textAreaRef.current) {
            return;
        }

        const { value } = textAreaRef.current as HTMLTextAreaElement;

        // saxios.post('/notes', {
        // })
        (textAreaRef.current as HTMLTextAreaElement).value = '';
    }

    return (
        <div className="mb-4">
            <textarea
                ref={textAreaRef}
                className="w-full p-3 rounded-md bg-gray-700 border border-gray-600 text-gray-200 placeholder-gray-400 focus:outline-none focus:border-blue-500 mb-2"
                rows={4}
                placeholder="Write your note here..."
            />
            <Button text="submit" onClick={handleCreateNote} />
        </div>
    )
}