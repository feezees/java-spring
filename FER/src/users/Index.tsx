import axios from "axios";
import { FC, useEffect, useState } from "react";
import { PostDto } from "../types";
import { Button } from "../ui/Button";
import { Post } from "../posts/Post";
import { Flex } from "../ui/Flex";
import { Text } from "../ui/Text";
import { Divider } from "../ui/Divider";

export const Users: FC = () => {
    const [users, setUsers] = useState<string[] | undefined>();
    const [selectedUser, setSelectedUser] = useState<string | undefined>();


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
        setSelectedUser(userId);

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
            <div className="mt-4 ">
                {
                    selectedUser && <>
                        <Flex justify="between">
                            <Flex direction="col" justify="center">
                                <Button text="back" onClick={() => {
                                    setSelectedUser(undefined);
                                    setPosts(undefined);
                                }} />
                            </Flex>

                            <Flex gap="md">
                                <Text paddingY="4" color="slate-400" text={'POST(s) By '} fontSize="lg" />
                                <Text paddingY="4" color="slate-200" text={' ' + selectedUser} fontSize="lg" />
                            </Flex>
                        </Flex>
                    </>
                }

                {!selectedUser &&
                    <Flex gap="md" direction="col">
                        {users?.map(el => <Button text={el} onClick={() => getPosts(el)} />)}
                    </Flex>
                }
            </div>

            <>
                {posts && posts.length > 0 && posts.map(post => <Post post={post} />)}
                {(posts && posts.length === 0) && <Flex justify="between"><div></div><Text text="no posts found" fontSize="sm" /></Flex>}
            </>
        </>
    )
}
