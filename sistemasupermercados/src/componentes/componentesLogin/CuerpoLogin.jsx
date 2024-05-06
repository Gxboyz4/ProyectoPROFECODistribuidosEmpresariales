import React from "react";
import { ContenedorLogin } from "./ContenedorLogin";
import '../../estilos/estilosLogin/CuerpoLogin.css';
import { Footer } from '../../componentes/Footer';
import { useNavigate } from 'react-router-dom';

export const CuerpoLogin = () => {
    const navigate = useNavigate();
    const sesion = localStorage.getItem('token')
     
    if(sesion){
      navigate('/menu')
    }

    return (
      <div className="cuerpo-login">
        <ContenedorLogin />
        <Footer />
      </div>
    );
  };