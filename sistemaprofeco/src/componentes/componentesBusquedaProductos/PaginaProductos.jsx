
import React, { useState } from 'react';
import { Header } from '../Header';
import { CuerpoPagina } from './CuerpoPagina';
import { Footer } from '../Footer';


export const PaginaProductos = () => {
    const [valorBusqueda, setValorBusqueda] = useState('');

    const manejadorBusqueda = (valor) => {
        setValorBusqueda(valor);
    };

    return (
        <div>
            <Header onSearch={manejadorBusqueda} />
            <CuerpoPagina valorBusqueda={valorBusqueda} />
            <Footer />
        </div>
    );
};