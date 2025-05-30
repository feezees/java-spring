import { useLayoutEffect, useState } from "react";
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";

export const Counter = () => {
    const [counter, setCounter] = useState('');

    const handleCounter = async () => {
        try {
            const data = await saxios.get<string>('/counter');
            setCounter(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    useLayoutEffect(() => {
        handleCounter();
    }, []);

    return <div>
        <Button text={'💸💰 Counter:  ' + counter} onClick={handleCounter} />
    </div>
}