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
import { Layout } from "../ui/Layout";
import { Header } from "../ui/Header";
import { Adding } from "./Add/Index";

export const Toppings = () => {
    const [step, setStep] = useState<'init' | 'adding' | 'checkout'>('init');

    const handleSetStep = (value: 'init' | 'adding' | 'checkout') => {
        setStep(value);
    }

    return (
        <>
            <Header>
                <Flex gap='md'>
                    <Button onClick={() => handleSetStep('init')} text='init' scheme={step === 'init' ? 'secondary' : 'primary'} />
                    <Button onClick={() => handleSetStep('adding')} text='adding' scheme={step === 'adding' ? 'secondary' : 'primary'} />
                    <Button onClick={() => handleSetStep('checkout')} text='checkout' scheme={step === 'checkout' ? 'secondary' : 'primary'} />
                </Flex>
            </Header>

            {/* {step === 'init' && <div></div>} */}
            {step === 'adding' && <Adding handleSetStep={handleSetStep} />}
            {/* {step === 'checkout' && <Button onClick={() => handleSetStep('init')} text='back' />} */}
        </>
    )
}