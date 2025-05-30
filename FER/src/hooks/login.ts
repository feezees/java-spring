import axios from "axios";
import { useState } from "react";
import { UserRoles } from "../types";

export const useAuth = () => {
    const [authLoading, setAuthloading] = useState<boolean>(true);
    const [loggedIn, setLoggedIn] = useState<boolean>(false);

    const handleLoginAs = (role: UserRoles) => () => {
        axios({
            method: 'PUT',
            url: 'http://localhost:8080/api/cookie?role=' + role,
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

    const logout = () => setLoggedIn(false);

    return { handleLoginAs, authLoading, setAuthloading, loggedIn, setLoggedIn, handleLogout, handleCheckAuth, logout }
}