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
import { Routes } from "../types"


interface IndexProps {
    showProfile: boolean,
    toggleShowProfile: () => void,
    handleLogout: () => void
    route: Routes,
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
                <Button onClick={() => setRoute('users')} text='users' />
                <Button onClick={() => setRoute('toppings')} text='toppings' />

            </Flex>

            <Divider />

            {route === '' && <Counter />}
            {route === 'notes' && <Notes logout={logout} />}
            {route === 'posts' && <Posts logout={logout} />}
            {route === 'tenders' && <Tenders logout={logout} />}
            {route === 'users' && <Users />}
            {route === 'toppings' && <Toppings />}

        </Layout >
    )
}