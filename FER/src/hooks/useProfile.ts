import { useState } from 'react';

export const useProfile = () => {
  const [showProfile, setShowProfile] = useState<boolean>(false);
  
  const toggleShowProfile = () => setShowProfile(prev => !prev);

  return {
    showProfile,
    toggleShowProfile,
  };
}; 