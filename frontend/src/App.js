import React, { useEffect, useState } from 'react';
import Photo from './Photo/Photo.js';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MetadataForm from './Photo/MetadataForm.js';
import './App.css';
import Navbar from './Navbar.js';

function App() {
  const [numPhotos, setNumPhotos] = useState(0);
  const [photoIds, setPhotoIds] = useState([]); // Store photo IDs
  const [backgroundImage, setBackgroundImage] = useState('');

  useEffect(() => {
    fetch(`http://192.168.0.39:8080/api/v1/photos/numPhotos`)
      .then(response => response.json()) 
      .then(num => { 
        setNumPhotos(num); 
        setPhotoIds(Array.from({ length: num }, (_, i) => i + 1)); 
      })
      .catch(err => {
        console.error("Error fetching number of photos:", err);
      });

      const imageUrl = `http://192.168.0.39:8080/api/v1/photos/9`;
      setBackgroundImage(imageUrl);
  }, []);
  
  return (
    <Router>
      <div className="App">
        <Navbar />
        <header className="App" style={{ backgroundImage: `url(${backgroundImage})` }}>
          <Routes>
            {/* Main page with all photos */}
            <Route 
              path="/" 
              element={
                <div className="photo-gallery">
                  {photoIds.map((id) => (
                    <Photo key={id} photoId={id} />
                  ))}
                </div>
              } 
            />
            {/* Metadata form page */}
            <Route path="/metadata/:photoId" element={<MetadataForm />} />
          </Routes>
        </header>
      </div>
    </Router>
  );
}

export default App;