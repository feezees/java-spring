import React, { useLayoutEffect, useState } from 'react';
import axios from 'axios';
import { Button } from './ui/Button';
import { Layout } from './ui/Layout';
import { Header } from './ui/Header';
import { NoteDto } from './types';
// import './App.css';

function App() {
  const [authLoading, setAuthloading] = useState<boolean>(true);
  const [loggedIn, setLoggedIn] = useState<boolean>(false);
  const [notes, setNotes] = useState<NoteDto | NoteDto[] | undefined>();

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
    axios({
      method: 'GET',
      url: `http://localhost:8080/api/notes/${id}`,
      withCredentials: true,
      headers: {
        'Content-Type': 'text/plain',
      },
    })
      .then(response => {
        console.log(response.data);
        setNotes(response.data as NoteDto);
      })
      .catch(error => {
        setLoggedIn(false);
        console.error('Ошибка:', error.response ? error.response.data : error.message);
      });
  }

  const handleGetNotesByUserId = (userId: string) => {
    axios({
      method: 'GET',
      url: `http://localhost:8080/api/notes/user/${userId}`,
      withCredentials: true,
      headers: {
        'Content-Type': 'text/plain',
      },
    })
      .then(response => {
        console.log(response.data);
        setNotes(response.data as NoteDto[]);
      })
      .catch(error => {
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

      <div className='flex gap-2 my-4'>
        <Button onClick={() => handleGetNotesById('1')} text='handleGetNotesByUserId' />
        <Button onClick={() => handleGetNotesByUserId('12fba3c3-f3db-4907-9e90-b668efd8c83e	')} text='handleGetNotesByUserId' />
      </div>

      <>
        {
          notes && Array.isArray(notes) && notes.map(el => <div key={el.id}>
            <p>{el.id}</p>
            <p>{el.userId}</p>
            <p>{el.value}</p>
          </div>
          )
        }

        {
          notes && !Array.isArray(notes) && <div>
            <p>{notes.id}</p>
            <p>{notes.userId}</p>
            <p>{notes.value}</p>
          </div>
        }
      </>
    </Layout>
  );
}

export default App;
