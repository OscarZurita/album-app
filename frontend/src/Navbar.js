import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css"; // Import styles

const Navbar = () => {
  return (
    <nav className="navbar">
      <h1 className="logo">Photo Album</h1>
      <ul className="nav-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/about">About</Link></li>
        <li ><Link to="/profile">Profile</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;
