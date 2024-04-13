import React from "react";
import { Header } from "../../componentes/Header";
import { Footer } from "../../componentes/Footer";
import '../../estilos/estilosMenu/CuerpoMenu.css'
import { useLocation } from 'react-router-dom';

export const CuerpoMenu = () => {
  const location = useLocation();
  const userData = location.state ? location.state.data : null;

  return (
    <div className="cuerpo-menu">
      <Header userData={userData} />
      <Footer />
    </div>
  );
};