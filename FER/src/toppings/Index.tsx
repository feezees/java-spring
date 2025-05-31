import { useEffect, useLayoutEffect, useMemo, useState } from "react"
import { saxios } from "../api/axios"
import { Topping } from "../types";
import { Flex } from "../ui/Flex";
import { Loading } from "../root/Loading";
import { Divider } from "../ui/Divider";
import { Text } from "../ui/Text";
import { Button } from "../ui/Button";
import React from "react";
import { useCounter } from "../root/CounterContext";

export const Toppings = () => {
    const [toppings, setToppings] = useState<'loading' | Topping[]>('loading');
    const { handleCounter, counterValue, setCounterValue } = useCounter();

    useLayoutEffect(() => {
        handleCounter();
    }, [])

    useEffect(() => {
        saxios.get('/toppings').then((res: any) => setToppings(res)).catch(() => { });
    }, [])

    const categoriesList: string[] = useMemo(() => {
        try {
            let uniqueList: string[] = [];

            if (Array.isArray(toppings)) {
                const list = toppings.map(t => t.category) as string[];
                const s = new Set(list);
                uniqueList = Array.from(s);

            }

            return uniqueList;

        } catch (e: any) {
            return [];
        }
    }, [toppings])

    const itemsByCategory = (value: string): Topping[] => {
        let result: Topping[] = [];

        if (Array.isArray(toppings)) {
            toppings.forEach((t) => {
                if (t.category === value) {
                    result.push(t);
                }
            })
        }

        return result;
    }

    const [coffieToppings, setCoffieToppings] = useState<Record<string, number>>({});

    const totalPrice = useMemo(() => {
        let result = 0;

        if (toppings && Array.isArray(toppings)) {

            const getToppingTotal = (id: number) => {
                let t = toppings.find(t => t.id === +id) as Topping;

                let tPrice = t.price as number;
                let tCount = coffieToppings[t.id];
                let tFullPrice = tPrice * tCount;

                return tFullPrice;
            };

            Object.keys(coffieToppings).forEach((tId) => result += getToppingTotal(+tId));
        }

        return String(result.toFixed(2));
    }, [coffieToppings, toppings])

    const handleSetTopping = (id: number, action: 'inc' | 'dec') => {
        const c = { ...coffieToppings };

        if (action === 'dec') {
            console.log(id);
            if (c[id] && c[id] > 0) {
                c[id] = c[id] - 1;
            }
        }

        if (action === 'inc') {
            // max
            if (c[id] && Array.isArray(toppings) && c[id] === (toppings.find(t => t.id === +id) as Topping).quantity) {
                return;
            }

            if (c[id] && Array.isArray(toppings) && c[id] < (toppings.find(t => t.id === +id) as Topping).quantity) {
                c[id] = c[id] + 1;
            } else {
                c[id] = 1;
            }
        }

        setCoffieToppings(c);
    }

    const handleCheckout = async () => {
        let c = new Map();

        Object.entries(coffieToppings).map(([k, v]: [k: string, v: number]) => {
            if (v > 0) {
                c.set(k, v);
            };
        })

        let notNullCoffieToppings = Object.fromEntries(c.entries());

        const updates = Object.keys(notNullCoffieToppings).map(id => ({
            id: parseInt(id),
            count: notNullCoffieToppings[id]
        }));

        try {
            const response: any = await saxios.post('/toppings/quantity', updates);
            if (response.data) {
                saxios.get<string>('/counter').then((res: any) => setCounterValue(res)).catch(() => { });
            }
        } catch (error) {
            console.error('Error during checkout:', error);
        }
    }

    return <Flex direction="col">
        {toppings === 'loading' && <Loading />}
        {Array.isArray(toppings) && <Flex direction="col" gap="md">
            <Flex justify="between">
                <Flex gap="md">
                    <Text color="slate-400" text="total" />
                    <Text text={totalPrice + ' 💸💰'} />
                </Flex>

                <Text text={'Balance : ' + counterValue + ' 💸💰'} />

                <Button text="checkout" onClick={handleCheckout} />
            </Flex>

            {categoriesList.map((c) => <React.Fragment key={['topping', c].join('-')}>
                <Text text={c} color="slate-400" />

                {itemsByCategory(c).map((t) =>
                    <Flex key={[c, t.id].join('-')} gap="md" align="center" justify="between">
                        <Flex gap="sm" align="center">
                            <Button text="-" onClick={() => handleSetTopping(t.id, "dec")} />

                            <div className="h-auto flex justify-center pt-2 gap-4 pt-1 px-4 w-16 text-center">
                                {coffieToppings[t.id] ? coffieToppings[t.id].toFixed(2) : '0'}
                            </div>

                            <Button text="+" onClick={() => handleSetTopping(t.id, "inc")} />

                            <div className="h-auto flex gap-4 pt-1 pl-4">
                                <Text text={'of '} color="slate-600" />
                                <Text text={t.quantity + ''} />
                            </div>
                        </Flex>
                        <p>{t.name}</p>
                        <p>{t.price}</p>
                    </Flex>
                )}

                <Divider />
            </React.Fragment>)}
        </Flex>
        }
    </Flex>
}