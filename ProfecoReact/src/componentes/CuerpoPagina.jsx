import React, { useState } from 'react';
import { FiltrosBarra } from "./FiltrosBarra";
import { ContenedorCards } from "./ContenedorCards";
import '../estilos/CuerpoPagina.css';



export const CuerpoPagina = () => {
    const [filtros, setFiltros] = useState({ supermercado: '', categoria: '' });

    return (
        <div className="cuerpo-pagina">
            <FiltrosBarra onFilterChange={setFiltros} />
            <div className="separador"></div>
            <ContenedorCards filtros={filtros} />
        </div>
    );
};