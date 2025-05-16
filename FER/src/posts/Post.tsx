import { FC } from "react";
import { PostDto } from "../types";

export const Post: FC<{ post: PostDto }> = ({ post: { id, author, postBody } }) => {
    return (
        <div>
            <div className="flex gap-2">
                <p>id: {id}</p>
                <p>author: {author}</p>
            </div>

            <div className="border-2 p-4 flex flex-col gap-2">
                {postBody.map(({ bodyType, bodyValue }) => <div>
                    {bodyType === 'text' && <p>{bodyValue}</p>}
                    {bodyType === 'image' && <img className="h-24 w-24" src={bodyValue} alt='bodyValue' />}
                </div>)}
            </div>
        </div>
    )
} 