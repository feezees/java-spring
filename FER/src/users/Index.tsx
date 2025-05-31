import { FC, useEffect, useState } from "react";
import { saxios } from "../api/axios";
import { Post } from "../posts/Post";
import { PostDto } from "../types";
import { Button } from "../ui/Button";
import { Flex } from "../ui/Flex";
import { Text } from "../ui/Text";

export const Users: FC = () => {
    const [users, setUsers] = useState<string[] | undefined>();
    const [selectedUser, setSelectedUser] = useState<string | undefined>();

    const getUsers = () => {
        saxios.get(`/users`)
            .then((response: any) => {
                console.log(response);
                setUsers(response);
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

        saxios.get(`/posts/${userId}`)
            .then((response: any) => {
                setPosts(response);
            })
            .catch(error => {
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    useEffect(() => {
        getUsers();
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
