import React from "react";
import { ContenedorLogin } from "./ContenedorLogin";
import '../../estilos/estilosLogin/CuerpoLogin.css';
import { Footer } from '../../componentes/Footer';

export const CuerpoLogin = () => {
    return (
      <div className="cuerpo-login">
        <ContenedorLogin />
        <Footer />
      </div>
    );
  };