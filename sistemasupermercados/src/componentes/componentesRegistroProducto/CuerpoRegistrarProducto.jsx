import React from 'react';
import { ContenedorRegistrarProducto } from './ContenedorRegistrarProducto';
import '../../estilos/estilosRegistroProducto/CuerpoRegistrarProducto.css';
import { useLocation } from 'react-router-dom'; // Importa useLocation para acceder a las props de navegación
import { TablaProductos } from './TablaProductos';
import { Footer } from '../../componentes/Footer';

export const CuerpoRegistrarProducto = () => {
    const location = useLocation();
    return (
        <div className="cuerpo-registrar">
            <ContenedorRegistrarProducto estado={location.state}/>
            <TablaProductos estado={location.state}/>
        </div>
    );
};