import React, { createContext, useState, useContext, ReactNode } from 'react';
import { saxios } from '../api/axios';

interface CounterContextType {
    handleCounter: () => void;
    counterValue: string;
    setCounterValue: (value: string) => void;
}

const CounterContext = createContext<CounterContextType | undefined>(undefined);

export const CounterProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [counterValue, setCounterValue] = useState<string>('');

    const handleCounter = async () => {
        try {
            const data = await saxios.get<string>('/counter');
            setCounterValue(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    return (
        <CounterContext.Provider value={{ handleCounter, counterValue, setCounterValue }}>
            {children}
        </CounterContext.Provider>
    );
};

export const useCounter = () => {
    const context = useContext(CounterContext);
    if (context === undefined) {
        throw new Error('useCounter must be used within a CounterProvider');
    }
    return context;
}; 