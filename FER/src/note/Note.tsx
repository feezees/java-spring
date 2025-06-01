import { FC } from "react";
import { NoteDto } from "../types";

export const Note: FC<{ note: NoteDto }> = ({ note: { id, userId, value } }) => {
    return (
        <div className="bg-gray-800 shadow-md rounded-lg p-4 mb-4">
            <div className="flex items-center justify-between mb-2">
                <p className="text-sm text-gray-400">ID: {id}</p>
                <p className="font-semibold text-white">User ID: {userId}</p>
            </div>

            <div className="border border-gray-700 rounded-md p-3">
                <p className="text-gray-300 leading-relaxed">{value}</p>
            </div>
        </div>
    )
}