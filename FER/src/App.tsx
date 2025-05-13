import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App bg-slate-900 min-h-screen text-slate-300 font-mono">
      <button onClick={async () => {
        try {
          const response = await fetch("http://localhost:8080/api/cookie/check", {
            method: "GET",
            credentials: "include",
            headers: {
              "Content-Type": "text/plain",
            },
          });

          const responseValue = await response.text();
          console.log(responseValue);
        } catch (error) {
          console.error("Error:", error);
        }
      }
      }>
        zxc
      </button>
    </div >
  );
}

export default App;
