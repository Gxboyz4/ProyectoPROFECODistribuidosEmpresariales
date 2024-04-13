import React from 'react';
import '../estilos/Header.css';
import logoProfeco from '../img/logo-profeco.png';
import { useNavigate } from 'react-router-dom';

export const Header = ({ userData }) => {
  const navigate = useNavigate();

  const handleGestionOfertasClick = () => {
    // Aquí puedes redirigir a la página de gestión de ofertas
    navigate('/gestionofertas', { state: { data: userData } });
  };

  const handleRegistrarProductosClick = () => {
    // Aquí puedes redirigir a la página de registrar productos
    navigate('/registroproductos', { state: { data: userData } });
  };

  return (
    <header className="header-container">
      <div className="logo-container">
      <img src={logoProfeco} alt="Profeco" className="logo" />
      </div>
      <div className="menu-options">
        <button onClick={handleGestionOfertasClick}>Gestión de Ofertas</button>
        <button onClick={handleRegistrarProductosClick}>Registrar Productos</button>
      </div>
    </header>
  );
};