import { FC } from "react"
import { Notes } from "../note/Index"
import { Posts } from "../posts/Index"
import { Tenders } from "../tenders/Index"
import { Button } from "../ui/Button"
import { Divider } from "../ui/Divider"
import { Flex } from "../ui/Flex"
import { Header } from "../ui/Header"
import { Layout } from "../ui/Layout"
import { Users } from "../users/Index"
import { Profile } from "./Profile"

interface IndexProps {
    showProfile: boolean,
    toggleShowProfile: () => void,
    handleLogout: () => void
    route: "" | "notes" | "posts" | "tenders" | "users",
    setRoute: (value: IndexProps['route']) => void,
    logout: () => void
}

export const Index: FC<IndexProps> = ({ showProfile, setRoute, toggleShowProfile, handleLogout, route, logout }) => {
    return (
        <Layout>
            <Header>
                <Button onClick={() => setRoute('')} text='home' />

                <div className='relative'>
                    {showProfile && <Profile />}
                    <Flex gap='md'>
                        <Button onClick={toggleShowProfile} text='profile' />
                        <Button onClick={handleLogout} text='logout' />
                    </Flex>
                </div>
            </Header>

            <Flex gap='md'>
                <Button onClick={() => setRoute('notes')} text='notes' />
                <Button onClick={() => setRoute('users')} text='users' />
            </Flex>

            <Divider />

            {route === 'notes' && <Notes logout={logout} />}
            {route === 'posts' && <Posts logout={logout} />}
            {route === 'tenders' && <Tenders logout={logout} />}
            {route === 'users' && <Users />}

        </Layout >
    )
}