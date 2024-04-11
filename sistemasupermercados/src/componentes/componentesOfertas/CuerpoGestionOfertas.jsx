import React from 'react';
import { ContenedorGestionOfertas } from './ContenedorGestionOfertas';
import '../../estilos/estilosOfertas/CuerpoGestionOfertas.css';
import { useLocation } from 'react-router-dom'; // Importa useLocation para acceder a las props de navegación
import { TablaProductos } from './TablaProductos';
import { TablaOfertas } from './TablaOfertas';
import { useState } from 'react';
import { Footer } from '../Footer';

export const CuerpoGestionOfertas = () => {
    const location = useLocation();
    const [ofertaSeleccionada, setOfertaSeleccionada] = useState(null);
    const [ofertaActualizada, setOfertaActualizada] = useState(null);
    const [productoSeleccionado, setProductoSeleccionado] = useState(null);

    const manejarEditar = (oferta) => {
        setOfertaSeleccionada(oferta);
    };

    const manejarActualizacionVista = (oferta) => {
        setOfertaActualizada(oferta);
    };

    const manejarProductoSeleccionado = (producto) => {
        setProductoSeleccionado(producto);
    };
    



    return (
        <div className="cuerpo-gestionofertas">
            <ContenedorGestionOfertas estado={location.state} ofertaSeleccionada={ofertaSeleccionada} productoSeleccionado={productoSeleccionado} onEdit={manejarActualizacionVista}/>
            <div>
            <TablaProductos estado={location.state} onEdit={manejarProductoSeleccionado} />
            <TablaOfertas estado={location.state} onEdit={manejarEditar} ofertaActualizada={ofertaActualizada}/>
            </div>
        </div>
    );
};