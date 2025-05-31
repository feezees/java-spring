import React, { FC, useState } from "react";
import { Button } from "../ui/Button";
import { PostDto } from "../types";
import { Post } from "./Post";
import { saxios } from "../api/axios";

interface PostsProps {
    logout: () => void;
}

export const Posts: FC<PostsProps> = ({ logout }) => {
    const [posts, setPosts] = useState<PostDto[]>();

    const handleGetPosts = () => {
        saxios.get(`/posts`)
            .then((response: any) => {
                console.log(response);
                setPosts(response as PostDto[]);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }
    return <>
        <div className='flex gap-2 my-4'>
            <Button onClick={handleGetPosts} text='handleGetPosts' />
        </div>
        <div className="flex flex-col gap-2">
            {posts?.length && posts.map(post => <Post post={post} />)}
        </div>
    </>
}