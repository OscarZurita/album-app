import React, { useState, useEffect } from 'react';
import { useAuth } from './AuthContext';
import './AlbumList.css';

const AlbumList = () => {
    const [albums, setAlbums] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { token } = useAuth();

    useEffect(() => {
        const fetchAlbums = async () => {
            try {
                const response = await fetch(`${process.env.REACT_APP_BACKEND_URI}/api/albums`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch albums');
                }

                const data = await response.json();
                setAlbums(data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        fetchAlbums();
    }, [token]);

    if (loading) {
        return <div className="loading">Loading albums...</div>;
    }

    if (error) {
        return <div className="error">Error: {error}</div>;
    }

    return (
        <div className="album-list-container">
            <h1>Your Albums</h1>
            <div className="albums-grid">
                {albums.map((album) => (
                    <div key={album.id} className="album-card">
                        <div className="album-cover">
                            {album.coverImage ? (
                                <img src={album.coverImage} alt={album.title} />
                            ) : (
                                <div className="placeholder-image">No Image</div>
                            )}
                        </div>
                        <div className="album-info">
                            <h3>{album.title}</h3>
                            <p>{album.description}</p>
                            <div className="album-stats">
                                <span>{album.photoCount} photos</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AlbumList; 