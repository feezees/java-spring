import React, { useLayoutEffect, useState } from 'react';
import axios from 'axios';
import { Button } from './ui/Button';
import { Layout } from './ui/Layout';
import { Header } from './ui/Header';
// import './App.css';

function App() {
  const [authLoading, setAuthloading] = useState<boolean>(true);
  const [loggedIn, setLoggedIn] = useState<boolean>(false);

  const handleLoginAsAdmin = () => {
    axios({
      method: 'PUT',
      url: 'http://localhost:8080/api/cookie/admin',
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


  const handleGetNotesById = (id: string) => {
    setAuthloading(true);

    axios({
      method: 'GET',
      url: `http://localhost:8080/api/notes/${id}`,
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



  useLayoutEffect(() => {
    handleCheckAuth();
  }, [])

  if (authLoading) {
    return <Layout>
      <Header>
        <p>Loading...</p>
      </Header>
    </Layout>
  }

  if (!loggedIn) {
    return <Layout>
      <Header>
        <Button text='log in as admin' onClick={handleLoginAsAdmin} />
      </Header>
    </Layout>
  }

  return (
    <Layout>
      <Header>
        <Button onClick={handleCheckAuth} text='check cookie' />
        <Button onClick={handleLogout} text='logout' />
      </Header>
      <Button onClick={() => handleGetNotesById('1')} text='handleGetNotesByUserId' />

    </Layout>
  );
}

export default App;
