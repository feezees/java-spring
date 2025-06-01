import { FC, useEffect, useLayoutEffect, useState } from "react";
import { saxios } from "../api/axios";
import { NoteDto, UserDto } from "../types";
import { Button } from "../ui/Button";
import { Note } from "./Note";
import { AddNote } from "./AddNote";
import { Flex } from "../ui/Flex";

interface NotesProps {
    logout: () => void;
}

export const Notes: FC<NotesProps> = ({ logout }) => {
    const [notes, setNotes] = useState<NoteDto | NoteDto[] | undefined>();

    const [users, setUsers] = useState<UserDto[] | undefined>();
    // const [selectedUser, setSelectedUser] = useState<string | undefined>();

    const getUsers = () => {
        saxios.get(`/users`)
            .then((response: any) => {
                console.log(response);
                setUsers((response).map((u: UserDto) => ({ id: u.id, username: u.username })));
                // setNotes(response.data);
            })
            .catch(error => {
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    useLayoutEffect(() => {
        getUsers();
    }, [])

    const handleGetNotesById = (id: string) => {
        saxios.get(`/notes/${id}`)
            .then((response: any) => {
                console.log(response.data);
                setNotes(response as NoteDto);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    const handleGetNotesByUserId = (userId: string) => {
        saxios.get(`/notes/user/${userId}`)
            .then((response: any) => {
                console.log(response);
                setNotes(response as NoteDto[]);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    return (
        <>
            <AddNote />

            <Flex direction="col" gap="sm" additionalStyles='py-2'>
                {users?.map(u => <Button text={u.username} onClick={() => handleGetNotesByUserId(u.id)} />)}
            </Flex >

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