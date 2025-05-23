import axios from "axios";
import { FC, useState } from "react";
import { Button } from "../ui/Button";
import { Tender } from "./Tender";

interface TendersProps {
    logout: () => void;
}

export const Tenders: FC<TendersProps> = ({ logout }) => {
    const [tenders, setTenders] = useState<string[]>();

    const handleGetTenders = () => {
        axios({
            method: 'GET',
            url: `http://localhost:8080/api/tenders`,
            withCredentials: true,
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then(response => {
                console.log(response.data);
                setTenders(response.data as string[]);
            })
            .catch(error => {
                // logout();
                console.error('Ошибка:', error.response ? error.response.data : error.message);
            });
    }
    return <>
        <div className='flex gap-2 my-4'>
            <Button onClick={handleGetTenders} text='handleGetTendersByUserId' />
        </div>
        <div className="flex flex-col gap-2">
            {tenders?.length && tenders.map(tender => <Tender tender={tender} />)}
        </div>
    </>
}