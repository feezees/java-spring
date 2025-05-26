import axios from "axios";
import { FC, useEffect, useState } from "react";
import { PostDto } from "../types";
import { Button } from "../ui/Button";
import { Post } from "../posts/Post";

export const Users: FC = () => {
    const [users, setUsers] = useState<string[] | undefined>();

    const bar = () => {
        axios({
            method: 'GET',
            url: `http://localhost:8080/api/users`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setUsers(response.data);
                // setNotes(response.data);
            })
            .catch(error => {
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    const [posts, setPosts] = useState<PostDto[] | undefined>(undefined);

    const getPosts = (userId: string) => {
        setPosts(undefined);

        axios({
            method: 'GET',
            url: `http://localhost:8080/api/posts/${userId}`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setPosts(response.data);
            })
            .catch(error => {
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    useEffect(() => {
        bar();
    }, [])

    return (
        <>
            <div className='flex gap-2 my-4'>
                {users?.map(el => <Button text={el} onClick={() => getPosts(el)} />)}
            </div >

            <>
                {posts && posts.length && posts.map(post => <Post post={post} /> )}
                {posts && posts.length === 0 && <p>no posts found</p>}
            </>
        </>
    )
}
