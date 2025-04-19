import React, { useEffect, useState } from 'react';
import Photo from './Photo/Photo.js';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import MetadataForm from './Photo/MetadataForm.js';
import './App.css';
import Navbar from './Navbar.js';
import { AuthProvider, useAuth } from './AuthContext';
import HomePage from './HomePage';
import AlbumList from './AlbumList';

const PrivateRoute = ({ children }) => {
    const { isAuthenticated } = useAuth();
    return isAuthenticated ? children : <Navigate to="/" />;
};

function App() {
  const [numPhotos, setNumPhotos] = useState(0);
  const [photoIds, setPhotoIds] = useState([]); // Store photo IDs
  const [backgroundImage, setBackgroundImage] = useState('');

  useEffect(() => {
    fetch(`${process.env.REACT_APP_BACKEND_URI}/api/v1/photos/numPhotos`)
      .then(response => response.json()) 
      .then(num => { 
        setNumPhotos(num); 
        setPhotoIds(Array.from({ length: num }, (_, i) => i + 1)); 
      })
      .catch(err => {
        console.error("Error fetching number of photos:", err);
      });

      const imageUrl = `${process.env.REACT_APP_BACKEND_URI}/api/v1/photos/9`;
      setBackgroundImage(imageUrl);
  }, []);
  
  return (
    <AuthProvider>
      <Router>
        <div className="App">
          <Navbar />
          <header className="App" style={{ backgroundImage: `url(${backgroundImage})` }}>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route
                path="/albums"
                element={
                  <PrivateRoute>
                    <AlbumList />
                  </PrivateRoute>
                }
              />
              {/* Main page with all photos */}
              <Route 
                path="/photos" 
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
    </AuthProvider>
  );
}

export default App;