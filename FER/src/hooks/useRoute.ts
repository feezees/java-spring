import { useState } from 'react';
import { Routes } from '../types';

export const useRoute = () => {
  const [route, setRoute] = useState<Routes>('');

  return {
    route,
    setRoute,
  };
}; 