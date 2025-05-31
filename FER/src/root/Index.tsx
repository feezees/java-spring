import { FC } from "react"
import { Link, Route, Routes, useLocation } from 'react-router-dom'
import { Notes } from "../note/Index"
import { Posts } from "../posts/Index"
import { Tenders } from "../tenders/Index"
import { Toppings } from '../toppings/Index'
import { Button } from "../ui/Button"
import { Divider } from "../ui/Divider"
import { Flex } from "../ui/Flex"
import { Header } from "../ui/Header"
import { Layout } from "../ui/Layout"
import { Text } from "../ui/Text"
import { Users } from "../users/Index"
import { Counter } from "./Counter"
import { Profile } from "./Profile"

interface IndexProps {
    showProfile: boolean,
    toggleShowProfile: () => void,
    handleLogout: () => void
    logout: () => void
}

export const Index: FC<IndexProps> = ({ showProfile, toggleShowProfile, handleLogout, logout }) => {
    const { pathname } = useLocation();

    return (
        <Layout>
            <Header>
                <Link to='' >
                    <Button onClick={() => { }} text='home' />
                </Link>

                <div className='relative'>
                    {showProfile && <Profile />}
                    <Flex gap='md'>
                        <Button onClick={toggleShowProfile} text='profile' />
                        <Button onClick={handleLogout} text='logout' />
                    </Flex>
                </div>
            </Header>

            <Flex gap='md'>
                {
                    ['notes', 'users', 'toppings'].map(l => <Link to={'/' + l}>
                        <Text text={l} additionalStyles={pathname === '/' + l ? "underline" : ''} />
                    </Link>)
                }
            </Flex>

            <Divider />

            <Routes>
                <Route path="/" element={<Counter />} />
                <Route path="/notes" element={<Notes logout={logout} />} />
                <Route path="/posts" element={<Posts logout={logout} />} />
                <Route path="/tenders" element={<Tenders logout={logout} />} />
                <Route path="/users" element={<Users />} />
                <Route path="/toppings" element={<Toppings />} />
            </Routes>

        </Layout >
    )
}