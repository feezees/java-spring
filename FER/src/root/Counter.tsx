import { useLayoutEffect } from "react";
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";
import { useCounter } from "./CounterContext";

export const Counter = () => {
    const { counterValue, setCounterValue, handleCounter } = useCounter();

    const incrementCounter = async () => {
        try {
            const data = await saxios.put<string>('/counter/increment');
            setCounterValue(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    useLayoutEffect(() => {
        handleCounter();
    }, []);

    return <div>
        <Button text={'💸💰 Counter:  ' + counterValue} onClick={incrementCounter} />
    </div>
}