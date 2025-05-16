import { FC } from "react";
import { NoteDto } from "../types";

export const Note: FC<{ note: NoteDto }> = ({ note: { id, userId, value } }) => {
    return (
        <div>
            <p>{id}</p>
            <p>{userId}</p>
            <p>{value}</p>
        </div>
    )
}