import React from 'react';
import LoginButton from './LoginButton';
import './HomePage.css';

const HomePage = () => {
    return (
        <div className="home-container">
            <div className="home-content">
                <h1>Welcome to Album App</h1>
                <p className="description">
                    Your personal space to create, organize, and share your favorite albums.
                    Log in to get started!
                </p>
                <div className="features">
                    <div className="feature-card">
                        <h3>Create Albums</h3>
                        <p>Organize your photos into beautiful albums</p>
                    </div>
                    <div className="feature-card">
                        <h3>Share Memories</h3>
                        <p>Share your albums with friends and family</p>
                    </div>
                    <div className="feature-card">
                        <h3>Easy Access</h3>
                        <p>Access your albums from anywhere, anytime</p>
                    </div>
                </div>
                <div className="login-section">
                    <LoginButton />
                </div>
            </div>
        </div>
    );
};

export default HomePage; 