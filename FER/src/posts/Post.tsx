import { FC } from "react";
import { PostDto } from "../types";

export const Post: FC<{ post: PostDto }> = ({ post: { id, author, postBody } }) => {
    return (
        <div className="bg-gray-800 shadow-md rounded-lg p-4 mb-4">
            <div className="flex items-center justify-between mb-2">
                <p className="text-sm text-gray-400">ID: {id}</p>
                <p className="font-semibold text-white">Author: {author}</p>
            </div>

            <div className="border border-gray-700 rounded-md p-3 flex flex-col gap-3">
                {postBody.map(({ bodyType, bodyValue }, index) => (
                    <div key={index}>
                        {bodyType === 'text' && <p className="text-gray-300 leading-relaxed">{bodyValue}</p>}
                        {bodyType === 'image' && <img className="w-48 h-48 rounded-md object-cover" src={bodyValue} alt='Post content' />}
                    </div>
                ))}
            </div>
        </div>
    )
} 