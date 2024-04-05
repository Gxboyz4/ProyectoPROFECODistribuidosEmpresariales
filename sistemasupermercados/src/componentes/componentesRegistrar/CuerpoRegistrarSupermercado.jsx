import React from 'react';
import { ContenedorRegistrarSupermercado } from './ContenedorRegistrarSupermercado';
import "../../estilos/estilosRegistrar/CuerpoRegistrarSupermercado.css";
import { Footer } from '../Footer';

export const CuerpoRegistrarSupermercado = () => {
    return (
        <div className="cuerpo-registrar">
            <ContenedorRegistrarSupermercado />
            <Footer />
        </div>
    );
};