import axios from 'axios';
import { useLayoutEffect, useState } from 'react';
import { Notes } from './note/Index';
import { Posts } from './posts/Index';
import { Tenders } from './tenders/Index';
import { Button } from './ui/Button';
import { Header } from './ui/Header';
import { Layout } from './ui/Layout';
import { Users } from './users/Index';
import { useAuth } from './hooks/login';
import { Flex } from './ui/Flex';
import { UserRoles } from './types';
// import './App.css';

function App() {
  const { handleLoginAs, authLoading, setAuthloading, loggedIn, setLoggedIn } = useAuth();

  const handleCheckAuth = () => {
    axios({
      method: 'GET',
      url: 'http://localhost:8080/api/cookie/check',
      withCredentials: true,
      headers: {
        'Content-Type': 'text/plain',
      },
    })
      .then(response => {
        setAuthloading(false);
        setLoggedIn(true);
        console.log(response.data);
      })
      .catch(error => {
        setAuthloading(false);
        console.error('Ошибка:', error.response ? error.response.data : error.message);
      });
  }

  const handleLogout = () => {
    setAuthloading(true);

    axios({
      method: 'DELETE',
      url: 'http://localhost:8080/api/cookie',
      withCredentials: true,
      headers: {
        'Content-Type': 'text/plain',
      },
    })
      .then(response => {
        setAuthloading(false);
        setLoggedIn(false);
        console.log(response.data);
      })
      .catch(error => {
        setAuthloading(false);
        setLoggedIn(false);
        console.error('Ошибка:', error.response ? error.response.data : error.message);
      });
  }

  const logout = () => setLoggedIn(false);

  useLayoutEffect(() => {
    handleCheckAuth();
  }, []);

  const [route, setRoute] = useState<'notes' | 'posts' | 'tenders' | 'users'>('users');

  if (authLoading) {
    return <Layout>
      <Header>
        <p>Loading...</p>
      </Header>
    </Layout>
  }

  if (!loggedIn) {
    return <Layout centred>
      <Flex direction='col' gap='lg'>
        {(['admin', 'moderator', 'user'] as UserRoles[]).map(el => <Button text={'log in as ' + el } onClick={handleLoginAs(el)} />)}
      </Flex>
    </Layout>
  }

  return (
    <Layout>
      <Header>
        <Button onClick={handleCheckAuth} text='check cookie' />
        <Button onClick={handleLogout} text='logout' />
      </Header>

      <div>
        <Button onClick={() => setRoute('notes')} text='notes' />
        {/* <Button onClick={() => setRoute('posts')} text='posts' /> */}
        {/* <Button onClick={() => setRoute('tenders')} text='tenders' /> */}
        <Button onClick={() => setRoute('users')} text='users' />
      </div>

      {route === 'notes' && <Notes logout={logout} />}
      {route === 'posts' && <Posts logout={logout} />}
      {route === 'tenders' && <Tenders logout={logout} />}
      {route === 'users' && <Users />}

    </Layout >
  );
}

export default App;
