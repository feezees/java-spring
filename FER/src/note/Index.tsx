import axios from "axios";
import { NoteDto } from "../types";
import React, { FC, useState } from "react";
import { Button } from "../ui/Button";
import { Note } from "./Note";

interface NotesProps {
    logout: () => void;
}

export const Notes: FC<NotesProps> = ({ logout }) => {
    const [notes, setNotes] = useState<NoteDto | NoteDto[] | undefined>();

    const handleGetNotesById = (id: string) => {
        axios({
            method: 'GET',
            url: `http://localhost:8080/api/notes/${id}`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setNotes(response.data as NoteDto);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    const handleGetNotesByUserId = (userId: string) => {
        axios({
            method: 'GET',
            url: `http://localhost:8080/api/notes/user/${userId}`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setNotes(response.data as NoteDto[]);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }


    return (
        <>
            <div className='flex gap-2 my-4'>
                <Button onClick={() => handleGetNotesById('1')} text='handleGetNotesByUserId' />
                <Button onClick={() => handleGetNotesByUserId('12fba3c3-f3db-4907-9e90-b668efd8c83e	')} text='handleGetNotesByUserId' />
            </div>

            <>
                {
                    notes && Array.isArray(notes) && notes.map(el => <Note note={el} key={el.id} />
                    )
                }

                {
                    notes && !Array.isArray(notes) && <Note note={notes} />
                }
            </>
        </>
    )
}