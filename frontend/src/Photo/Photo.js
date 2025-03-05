import React, { useEffect, useState } from 'react';
import './Photo.css'; // Import the CSS file

const Photo = ({ photoId }) => {
    const [photo, setPhoto] = useState(null);
    const [imageSrc, setImageSrc] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Fetch photo metadata
        fetch(`http://localhost:8080/api/v1/photos/${photoId}/metadata`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch photo metadata');
                }
                return response.json(); // Parse JSON response
            })
            .then(photoData => {
                setPhoto(photoData); // Store metadata
            })
            .catch(err => {
                console.error('Error fetching photo metadata:', err);
                setError(err.message);
            });

        // Fetch actual photo
        fetch(`http://localhost:8080/api/v1/photos/${photoId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch photo');
                }
                return response.blob(); // Fetch image as a Blob
            })
            .then(imageBlob => {
                const imageUrl = URL.createObjectURL(imageBlob); // Create URL for the Blob
                setImageSrc(imageUrl); // Set the image source
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching photo:', err);
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="photo-container">
            {photo && <h1>{photo.title}</h1>}
            {imageSrc && <img src={imageSrc} alt="Photo" />}
        </div>
    );
};

export default Photo;
