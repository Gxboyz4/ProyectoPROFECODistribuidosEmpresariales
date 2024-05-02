import React from "react";
import '../../estilos/estilosLogin/CuerpoLoginProfeco.css';
import {LoginButton} from "./componentes/componentesAutentication/LoginButton"

export const CuerpoLoginProfeco = () => {
    return (
      <div className="cuerpo-login">
        <LoginButton/>
      </div>
    );
  };