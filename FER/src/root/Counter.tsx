import { useLayoutEffect } from "react";
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";
import { useCounter } from "./CounterContext";

export const Counter = () => {
    const { counterValue, setCounterValue } = useCounter();

    const handleCounter = async () => {
        try {
            const data = await saxios.get<string>('/counter');
            setCounterValue(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    useLayoutEffect(() => {
        handleCounter();
    }, []);

    return <div>
        <Button text={'💸💰 Counter:  ' + counterValue} onClick={handleCounter} />
    </div>
}