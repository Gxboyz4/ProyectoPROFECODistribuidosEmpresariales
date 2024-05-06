import React from "react";
import { Header } from "../../componentes/Header";
import { Footer } from "../../componentes/Footer";
import '../../estilos/estilosMenu/CuerpoMenu.css'

export const CuerpoMenu = () => {
  const userData = JSON.parse(localStorage.getItem('supermercado'));

  return (
    <div className="cuerpo-menu">
      <Header userData={userData} />
      <Footer />
    </div>
  );
};