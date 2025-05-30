import { useState } from "react";
import { UserRoles } from "../types";
import { saxios } from "../api/axios";

export const useAuth = () => {
    const [authLoading, setAuthloading] = useState<boolean>(true);
    const [loggedIn, setLoggedIn] = useState<boolean>(false);

    const handleLoginAs = (role: UserRoles) => async () => {
        try {
            await saxios.put(`/cookie?role=${role}`);
            setAuthloading(false);
            setLoggedIn(true);
        } catch (error) {
            setAuthloading(false);
            console.error('Ошибка:', error);
        }
    }

    const handleLogout = async () => {
        setAuthloading(true);
        try {
            await saxios.delete('/cookie');
            setAuthloading(false);
            setLoggedIn(false);
        } catch (error) {
            setAuthloading(false);
            setLoggedIn(false);
            console.error('Ошибка:', error);
        }
    }

    const handleCheckAuth = async () => {
        try {
            await saxios.get('/cookie/check');
            setAuthloading(false);
            setLoggedIn(true);
        } catch (error) {
            setAuthloading(false);
            console.error('Ошибка:', error);
        }
    }

    const logout = () => setLoggedIn(false);

    return { handleLoginAs, authLoading, setAuthloading, loggedIn, setLoggedIn, handleLogout, handleCheckAuth, logout }
}