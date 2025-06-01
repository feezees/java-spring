import React, { useEffect, useLayoutEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { useAuth } from './hooks/login';
import { useProfile } from './hooks/useProfile';
import { Index } from './root/Index';
import { Loading } from './root/Loading';
import { Login } from './root/Login';
import { CounterProvider } from './root/CounterContext';

function App() {
  const { handleLoginAs, authLoading, loggedIn, logout, handleLogout, handleCheckAuth } = useAuth();
  const { showProfile, toggleShowProfile } = useProfile();

  useLayoutEffect(() => {
    handleCheckAuth();
  }, []);

  useEffect(() => {
    console.log(authLoading);
  }, [authLoading]);

  if (authLoading) {
    return <Loading />;
  }

  if (!loggedIn) {
    return <Login handleLoginAs={handleLoginAs} />;
  }

  return (
    <BrowserRouter>
      <CounterProvider>
        <Index
          logout={logout}
          handleLogout={handleLogout}
          showProfile={showProfile}
          toggleShowProfile={toggleShowProfile}
        />
      </CounterProvider>
    </BrowserRouter>
  );
}

export default App;
