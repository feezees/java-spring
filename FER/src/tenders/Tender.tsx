import { FC } from "react";
import { PostDto } from "../types";

export const Tender: FC<{ tender: string }> = ({ tender }) => {
    return (
        <div>
            <div className="flex gap-2">
                <p>id: {tender}</p>
            </div>
        </div>
    )
} 