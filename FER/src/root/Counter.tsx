import axios from "axios";
import { useLayoutEffect, useState } from "react";
import { Button } from "../ui/Button";

export const Counter = () => {
    const [counter, setCounter] = useState('');

    const handleCounter = () => {
        axios({
            method: 'GET',
            url: 'http://localhost:8080/api/counter',
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                setCounter(response.data);
            })
            .catch(error => {
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }

    useLayoutEffect(()=> handleCounter(),[]);

    return <div>
        <Button text={'💸💰 Counter:  ' + counter} onClick={handleCounter} />
    </div>
}