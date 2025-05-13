import { FC } from "react"
import { SChildren } from "../types"

export const Header: FC<{ children: SChildren }> = ({ children }) => {
    return (
        <header className="flex w-full justify-between h-12">
            {children}
        </header>
    )
}