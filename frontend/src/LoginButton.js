import React, { useState } from 'react';
import { useAuth } from './AuthContext';
import './LoginButton.css';

const LoginButton = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login, logout, isAuthenticated } = useAuth();

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        
        try {
            const success = await login(email, password);
            if (success) {
                setIsModalOpen(false);
                setEmail('');
                setPassword('');
            } else {
                setError('Invalid email or password');
            }
        } catch (err) {
            setError('An error occurred during login. Please try again.');
            console.error('Login error:', err);
        }
    };

    const handleLogout = () => {
        logout();
    };

    return (
        <div className="login-container">
            {!isAuthenticated ? (
                <button 
                    className="login-button"
                    onClick={() => setIsModalOpen(true)}
                >
                    Login
                </button>
            ) : (
                <button 
                    className="logout-button"
                    onClick={handleLogout}
                >
                    Logout
                </button>
            )}

            {isModalOpen && (
                <div className="modal">
                    <div className="modal-content">
                        <span 
                            className="close-button"
                            onClick={() => setIsModalOpen(false)}
                        >
                            &times;
                        </span>
                        <h2>Login</h2>
                        {error && <div className="error-message">{error}</div>}
                        <form onSubmit={handleLogin}>
                            <div className="form-group">
                                <label htmlFor="email">Email:</label>
                                <input
                                    type="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="password">Password:</label>
                                <input
                                    type="password"
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <button type="submit" className="submit-button">
                                Login
                            </button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default LoginButton; 