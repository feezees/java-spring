import React, { FC } from "react"
import { SChildren } from "../types"

export const Layout: FC<{ children: SChildren }> = ({ children }) => {
    return (
        <div className="p-4 bg-slate-900 min-h-screen text-slate-300 font-[Open_Sans] text-xl mx-auto my-auto ">
            {children}
        </div>
    )
}