import React, { FC, useState } from "react";
import { Button } from "../ui/Button";
import axios from "axios";
import { PostDto } from "../types";
import { Post } from "./Post";

interface PostsProps {
    logout: () => void;
}

export const Posts: FC<PostsProps> = ({ logout }) => {
    const [posts, setPosts] = useState<PostDto[]>();

    const handleGetPosts = () => {
        axios({
            method: 'GET',
            url: `http://localhost:8080/api/posts`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setPosts(response.data as PostDto[]);
            })
            .catch(error => {
                logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }
    return <>
        <div className='flex gap-2 my-4'>
            <Button onClick={handleGetPosts} text='handleGetNotesByUserId' />
        </div>
        <div className="flex flex-col gap-2">
            {posts?.length && posts.map(post => <Post post={post} />)}
        </div>
    </>
}