import React from 'react';
import Photo from './Photo/Photo.js';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Photo Album</h1>
        <Photo key = {1} photoId={1}/>
      </header>
    </div>
  );
}

export default App;