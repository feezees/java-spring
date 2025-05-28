import { FC } from "react";
import { SChildren } from "../types";

interface FlexProps {
    children: SChildren;
    direction?: 'col' | '';
    gap?: 'sm' | 'md' | 'lg';
    justify?: 'around' | 'between' | 'center'
}

export const Flex: FC<FlexProps> = ({ children, direction, gap, justify }) => {
    const sDirection = direction === 'col' ? 'flex flex-col' : 'flex';
    const sGap = gap ? gap === 'lg' ? 'gap-[16px]' : gap === 'md' ? 'gap-[8px]' : gap === 'sm' ? 'gap-[4px]' : '' : '';
    const sJustify = justify ? ('justify-' + justify) : '';
    const sStyle = [sDirection, sGap, sJustify].join(' ');

    return (
        <div className={sStyle}>{children}</div>
    )
}
