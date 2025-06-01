import { useLayoutEffect } from "react";
import { Button } from "../ui/Button";
import { saxios } from "../api/axios";
import { useCounter } from "./CounterContext";

export const Counter = () => {
    const { counterValue, setCounterValue } = useCounter();

    const getCounter = async () => {
        try {
            const data = await saxios.get<string>('/counter');
            setCounterValue(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    const incrementCounter = async () => {
        try {
            const data = await saxios.put<string>('/counter/increment');
            setCounterValue(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    useLayoutEffect(() => {
        getCounter();
    }, []);

    return <div>
        <Button text={'💸💰 Counter:  ' + counterValue} onClick={incrementCounter} />
    </div>
}