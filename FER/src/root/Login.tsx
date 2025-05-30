import { FC } from "react"
import { UserRoles } from "../types"
import { Button } from "../ui/Button"
import { Flex } from "../ui/Flex"
import { Layout } from "../ui/Layout"

export const Login: FC<{ handleLoginAs: (login: UserRoles) => () => void }> = ({ handleLoginAs }) => {
    return (
        <Layout centred>
            <Flex direction='col' gap='lg'>
                {(['admin', 'moderator', 'user'] as UserRoles[]).map(el => <Button text={'log in as ' + el} onClick={handleLoginAs(el)} />)}
            </Flex>
        </Layout>
    )
}