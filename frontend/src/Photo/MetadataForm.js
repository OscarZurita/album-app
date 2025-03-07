import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import './MetadataForm.css'; // ✅ Import the CSS file

const MetadataForm = () => {
    const { photoId } = useParams();
    const [ metadata, setMetadata ] = useState({ title: '', lastModifiedDate: '', uploadDate: '', description: '', url: '' });
    const [ loading, setLoading ] = useState(true);
    const [ error, setError ] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/v1/photos/${photoId}/metadata`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch photo metadata');
                }
                return response.json();
            })
            .then(photoData => {
                setMetadata(photoData);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching photo metadata:', err);
                setError(err.message);
            });
    }, [photoId]);
    if(loading) {
        return <div>Loading...</div>;
    }
    if(error) {
        return <div>Error: {error}</div>;
    }
    return (
        <div className="metadata-container">
            <h1>Edit metadata</h1>
            <form>
                <label>
                    Title:
                    <input 
                        type="text"
                        value={metadata.title}
                        onChange={(e) => setMetadata({ ...metadata, title: e.target.value })} />
                </label>
                <br />
                <br />
                <label>
                    lastModifiedDate:
                    <input 
                        type="text"
                        value={metadata.lastModifiedDate} 
                        onChange={(e) => setMetadata({ ...metadata, lastModifiedDate: e.target.value })}/>
                </label>
                <br />
                <br />
                <label>
                    Upload Date:
                    <input 
                        type="text" 
                        value={metadata.uploadDate} 
                        onChange={(e) => setMetadata({ ...metadata, uploadDate: e.target.value })}/>
                </label>
                <br />
                <br />
                <label>
                    Description:
                    <input 
                        type="text" 
                        value={metadata.description} 
                        onChange={(e) => setMetadata({ ...metadata, description: e.target.value })}/>
                </label>
                <br />
                <br />
                <label>
                    URL:
                    <input 
                        type="text" 
                        value={metadata.url} 
                        onChange={(e) => setMetadata({ ...metadata, url: e.target.value })}/>
                </label>
                <br />
                <br />
                <button type="submit">Save</button>
            </form>
        </div>
    );

};

export default MetadataForm;