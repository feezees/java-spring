import { FC, useState } from "react";
import { Button } from "../ui/Button";
import { Tender } from "./Tender";
import { saxios } from "../api/axios";

interface TendersProps {
    logout: () => void;
}

export const Tenders: FC<TendersProps> = ({ logout }) => {
    const [tenders, setTenders] = useState<string[]>();

    const handleGetTenders = () => {
        saxios.get(`/tenders`)
            .then((response: any) => {
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