import { FC } from "react";

type paddingSizes = '4' | '8' | '16';

interface TextProps {
    text: string;
    paddingX?: paddingSizes;
    paddingY?: paddingSizes;
    fontSize?: 'sm' | 'md' | 'lg'
    color?: 'slate-200' | 'slate-400' | 'slate-600'
}

export const Text: FC<TextProps> = ({ text, fontSize, paddingX, paddingY, color }) => {
    const sBased = ' font-montserrat text-lg leading-loose tracking-widest uppercase     ';
    const sFontSize = !fontSize ? '' : fontSize === 'lg' ? 'text-2xl' : fontSize === 'md' ? 'text-md' : 'text-sm';
    const sPaddingX = paddingX ? ('px-' + paddingX) : '';
    const sPaddingY = paddingY ? `pt-${paddingY} pb-${paddingY}` : '';
    const sColor = color ? ('text-' + color) : ''
    const sStyle = [sBased, sFontSize, sPaddingX, sPaddingY, sColor].join(' ');

    return (
        <p className={sStyle}>{text}</p>
    )
}