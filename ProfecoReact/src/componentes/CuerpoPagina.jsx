import React, { useState, useEffect } from 'react';
import { FiltrosBarra } from "./FiltrosBarra";
import { ContenedorCards } from "./ContenedorCards";
import '../estilos/CuerpoPagina.css';

export const CuerpoPagina = ({ valorBusqueda }) => {
    const [filtros, setFiltros] = useState({ supermercado: '', categoria: '', nombreProducto: valorBusqueda });

    useEffect(() => {
        setFiltros(prevFiltros => ({
            ...prevFiltros,
            nombreProducto: valorBusqueda
        }));
    }, [valorBusqueda]);

    const handleFilterChange = (newFilters) => {
        setFiltros(newFilters);
    };

    return (
        <div className="cuerpo-pagina">
            <FiltrosBarra onFilterChange={handleFilterChange} nombreProducto={valorBusqueda} />
            <div className="separador"></div>
            <ContenedorCards filtros={filtros} />
        </div>
    );
};