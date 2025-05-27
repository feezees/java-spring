import { FC } from "react";
import { SChildren } from "../types";

export const Flex: FC<{ children: SChildren, direction?: 'col' | '', gap?: 'sm' | 'md' | 'lg' }> = ({ children, direction, gap }) => {
    const sDirection = direction === 'col' ? 'flex flex-col' : 'flex';
    const sGap = gap ? gap === 'lg' ? 'gap-[16px]' : gap === 'md' ? 'gap-[8px]' : gap === 'sm' ? 'gap-[4px]' : '' : '';
    const sStyle = [sDirection, sGap].join(' ');

    return (
        <div className={sStyle}>{children}</div>
    )
}
