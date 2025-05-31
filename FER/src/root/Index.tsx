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
import { Counter } from "./Counter"
import { Toppings } from '../toppings/Index';
import { Routes as AppRoutes } from "../types"
import { useRoute } from "../hooks/useRoute";
import { Route, Routes } from 'react-router-dom';
import { Link } from "react-router-dom"
import { Text } from "../ui/Text"

interface IndexProps {
    showProfile: boolean,
    toggleShowProfile: () => void,
    handleLogout: () => void
    logout: () => void
}

export const Index: FC<IndexProps> = ({ showProfile, toggleShowProfile, handleLogout, logout }) => {
    const { setRoute } = useRoute();

    return (
        <Layout>
            <Header>
                <Link to='' >
                    <Button onClick={()=>{}} text='home' />
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
                <Link to='/notes'>
                    <Text text='notes' />
                </Link>


                <Link to='/users'>
                    <Text text='users' />
                </Link>


                <Link to='/toppings'>
                    <Text text='toppings' />
                </Link>
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