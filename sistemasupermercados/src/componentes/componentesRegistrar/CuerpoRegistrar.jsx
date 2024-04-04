import React from 'react';
import { ContenedorRegistrar } from './ContenedorRegistrar';
import "../../estilos/estilosRegistrar/CuerpoRegistrar.css";
import { Footer } from '../../componentes/Footer';

export const CuerpoRegistrar = () => {
    return (
        <div className="cuerpo-registrar">
            <ContenedorRegistrar />
            <Footer />
        </div>
    );
};