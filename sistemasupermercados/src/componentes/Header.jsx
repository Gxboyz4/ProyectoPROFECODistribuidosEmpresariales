import React from 'react';
import '../estilos/Header.css';
import logoProfeco from '../img/logo-profeco.png';
import iconoSalir from '../img/icons/logout.png'
import { useNavigate } from 'react-router-dom';

export const Header = ({ userData }) => {
  const navigate = useNavigate();

  const handleGestionOfertasClick = () => {
    navigate('/gestionofertas', { state: { data: userData } });
  };

  const handleGestionProductosClick = () => {
    navigate('/registroproductos', { state: { data: userData } });
  };
  const salirClick = () => {
    navigate('/');
  }

  return (
    <header className="header-container">
      <div className="logo-container">
      <img src={logoProfeco} alt="Profeco" className="logo" />
      </div>
      <div className='menu-text'>
        Bienvenido, {userData.nombre}!
      </div>
      <div className="menu-options">
        <button onClick={handleGestionOfertasClick}>Gestión de Ofertas</button>
        <button onClick={handleGestionProductosClick}>Gestión de productos</button>
      </div>
      <div className='icono-salir' onClick={salirClick}>
      <img src={iconoSalir} alt="salir" className="icono-salir" />
      </div>
    </header>
  );
};