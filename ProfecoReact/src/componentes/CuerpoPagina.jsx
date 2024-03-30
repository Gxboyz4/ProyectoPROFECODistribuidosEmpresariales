import React from "react";
import { FiltrosBarra } from "./FiltrosBarra";
import { ContenedorCards } from "./ContenedorCards";
import '../estilos/CuerpoPagina.css';

export const CuerpoPagina = () => {
    return (
      <div className="cuerpo-pagina">
        <FiltrosBarra />
        <div className="separador"></div>
        <ContenedorCards />
      </div>
    );
  };