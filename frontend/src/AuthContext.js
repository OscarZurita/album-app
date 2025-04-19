import React, { createContext, useState, useContext, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token'));
    const [isAuthenticated, setIsAuthenticated] = useState(!!token);

    useEffect(() => {
        if (token) {
            localStorage.setItem('token', token);
            setIsAuthenticated(true);
        } else {
            localStorage.removeItem('token');
            setIsAuthenticated(false);
        }
    }, [token]);

    const login = async (email, password) => {
        try {
            const response = await fetch(`${process.env.REACT_APP_BACKEND_URI}/api/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error('Login failed');
            }

            const data = await response.json();
            if (data.token) {
                setToken(data.token);
                return true;
            }
            return false;
        } catch (error) {
            console.error('Login error:', error);
            return false;
        }
    };

    const logout = () => {
        setToken(null);
    };

    return (
        <AuthContext.Provider value={{ token, isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext); 