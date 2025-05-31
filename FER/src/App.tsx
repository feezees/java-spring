import { useEffect, useLayoutEffect } from 'react';
import { useAuth } from './hooks/login';
import { useProfile } from './hooks/useProfile';
import { useRoute } from './hooks/useRoute';
import { Index } from './root/Index';
import { Loading } from './root/Loading';
import { Login } from './root/Login';

function App() {
  const { handleLoginAs, authLoading, loggedIn, logout, handleLogout, handleCheckAuth } = useAuth();
  const { route, setRoute } = useRoute();
  const { showProfile, toggleShowProfile } = useProfile();

  useLayoutEffect(() => {
    handleCheckAuth();
  }, [handleCheckAuth]);

  useEffect(() => {
    console.log(authLoading);
  }, [authLoading])

  if (authLoading) {
    return <Loading />;
  }

  if (!loggedIn) {
    return <Login handleLoginAs={handleLoginAs} />;
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
