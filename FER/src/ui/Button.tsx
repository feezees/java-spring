import { FC } from "react"

interface ButtonProps {
    onClick: () => void;
    text: string;
    disabled?: boolean;
    scheme?: 'primary' | 'secondary';
}

export const Button: FC<ButtonProps> = ({ onClick, text, disabled, scheme = 'primary'    }) => {
    return (
        <button
            className={`rounded-sm py-2 px-8  hover:bg-slate-600 ${scheme === 'primary' ? 'bg-slate-800 duration-100' : 'bg-slate-700 duration-100'}`}
            onClick={onClick} disabled={disabled} >
            {text}
        </button>
    )
}