import React, { FC } from "react"
import { SChildren } from "../types"

const getStyle = (centred?: boolean) => {
    const sBased = "p-4 bg-slate-900 min-h-screen text-slate-300 font-[Open_Sans] text-xl mx-auto my-auto";
    const sCentred = centred ? "flex justify-center items-center" : "";
    const sStyle = [sBased, sCentred].join(' ');

    return sStyle;
}

export const Layout: FC<{ children: SChildren, centred?: boolean }> = ({ children, centred }) => {
    return (
        <div className={getStyle(centred)}>
            {children}
        </div>
    )
}