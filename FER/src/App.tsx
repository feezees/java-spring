import { useLayoutEffect, useState } from 'react';
import { useAuth } from './hooks/login';
import { Index } from './root/Index';
import { Loading } from './root/Loading';
import { Login } from './root/Login';
import { Routes } from './types';

function App() {
  const { handleLoginAs, authLoading, loggedIn, logout, handleLogout, handleCheckAuth } = useAuth();

  useLayoutEffect(() => {
    handleCheckAuth();
  }, [handleCheckAuth]);

  const [route, setRoute] = useState<Routes>('users');

  const [showProfile, setShowProfile] = useState<boolean>(false);
  const toggleShowProfile = () => setShowProfile(prev => !prev);

  if (authLoading) {
    return <Loading />
  }

  if (!loggedIn) {
    return <Login handleLoginAs={handleLoginAs} />
  }

  return (
    <Index
      logout={logout}
      handleLogout={handleLogout}
      route={route}
      setRoute={setRoute}
      showProfile={showProfile}
      toggleShowProfile={toggleShowProfile}
    />
  );
}

export default App;
