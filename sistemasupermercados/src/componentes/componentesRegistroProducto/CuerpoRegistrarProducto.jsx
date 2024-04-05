import React from 'react';
import { ContenedorRegistrarProducto } from './ContenedorRegistrarProducto';
import '../../estilos/estilosRegistroProducto/CuerpoRegistrarProducto.css';
import { useLocation } from 'react-router-dom'; // Importa useLocation para acceder a las props de navegación
import { TablaProductos } from './TablaProductos';
import { useState } from 'react';
import { Footer } from '../../componentes/Footer';

export const CuerpoRegistrarProducto = () => {
    const location = useLocation();
    const [productoSeleccionado, setProductoSeleccionado] = useState(null);

    const manejarEditar = (producto) => {
        setProductoSeleccionado(producto);
    };

    return (
        <div className="cuerpo-registrar">
            <ContenedorRegistrarProducto estado={location.state} productoSeleccionado={productoSeleccionado}/>
            <TablaProductos estado={location.state} onEdit={manejarEditar} />
        </div>
    );
};