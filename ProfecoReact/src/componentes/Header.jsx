
import React, { useState } from 'react';
import '../estilos/Header.css';

export const Header = ({ onSearch }) => {
  const [nombreProducto, setNombreProducto] = useState('');

  const manejarCambios = (event) => {
    setNombreProducto(event.target.value);
  };

  const manejarEnviar = () => {
    let valor = nombreProducto;
    let nuevaCadena = valor.replace(/ /g, ".");
    onSearch(nuevaCadena);
  };

  return (
    <header className="header-container">
      <div className="logo-container">
        <img src="logo.png" alt="Profeco" className="logo" />
      </div>
      <div className="search-container">
        <input
          type="text"
          placeholder="Buscar..."
          className="search-input"
          value={nombreProducto}
          onChange={manejarCambios}
        />
        <button className="search-button" onClick={manejarEnviar}>Buscar</button>
      </div>
    </header>
  );
};