import React from "react";
import { Header } from "../../componentes/Header";
import { Footer } from "../../componentes/Footer";
import { useLocation } from 'react-router-dom';

export const CuerpoMenu = () => {
  const location = useLocation();
  const userData = location.state ? location.state.data : null; // Obtener la información del usuario logeado

  return (
    <div className="cuerpo-menu">
      <Header userData={userData} /> {/* Pasar la información del usuario al HeaderMenu */}
      {/* Contenido adicional aquí */}
      <Footer />
    </div>
  );
};