import { FC } from "react";
import { SChildren } from "../types";

interface FlexProps {
    children: SChildren;
    direction?: 'col' | '';
    gap?: 'sm' | 'md' | 'lg';
    justify?: 'around' | 'between' | 'center'
    align?: 'center'
    additionalStyles?: string;
}

export const Flex: FC<FlexProps> = ({ children, direction, gap, justify, align, additionalStyles }) => {
    const sDirection = direction === 'col' ? 'flex flex-col' : 'flex';
    const sGap = gap ? gap === 'lg' ? 'gap-[16px]' : gap === 'md' ? 'gap-[8px]' : gap === 'sm' ? 'gap-[4px]' : '' : '';
    const sJustify = justify ? ('justify-' + justify) : '';
    const sAlign = align ? ('align-' + align) : '';
    const sStyle = [sDirection, sGap, sJustify, sAlign, additionalStyles ].join(' ');

    return (
        <div className={sStyle}>{children}</div>
    )
}
