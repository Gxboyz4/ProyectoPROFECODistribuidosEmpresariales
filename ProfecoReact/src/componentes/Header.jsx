import React from 'react';
import '../estilos/Header.css';

export const Header = () => {
  return (
    <header className="header-container">
      <div className="logo-container">
        <img src="logo.png" alt="Profeco" className="logo" />
      </div>
      <div className="search-container">
        <input type="text" placeholder="Buscar..." className="search-input" />
        <button className="search-button">Buscar</button>
      </div>
    </header>
  );
};