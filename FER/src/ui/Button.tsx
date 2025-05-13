import { FC } from "react"

interface ButtonProps {
    onClick: () => void;
    text: string;
    disabled?: boolean;
}

export const Button: FC<ButtonProps> = ({ onClick, text, disabled }) => {
    return (
        <button
            className="rounded-xl py-2 px-8 bg-slate-800 hover:bg-slate-700 duration-100"
            onClick={onClick} disabled={disabled} >{text}</button>
    )
}