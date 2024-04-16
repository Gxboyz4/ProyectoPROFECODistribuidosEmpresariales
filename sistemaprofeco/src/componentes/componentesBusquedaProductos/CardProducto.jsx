import React from "react";
import "../../estilos/estilosBusquedaProductos/CardProducto.css";
import { FrmInconsistencias } from "../componentesInconsistencias/FrmInconsistencia";

export const CardProducto = ({ nombre, nombreSupermercado, categoria, precio, imagenUrl }) => {
    const openModal = () => {
        // Lógica para abrir el modal
        // Aquí puedes hacer cualquier cosa antes de mostrar el modal
    };

    return (
        <div className="card-container">
            {/* Utilizar las propiedades para renderizar la información */}
            <img src="https://picsum.photos/200/300" alt="hola"/>
            <div className="card-info">
                <h1 className="card-nombre">{nombre}</h1>
                <p className="card-nombreSupermercado">{nombreSupermercado}</p>
                <p className="card-categoria">{categoria}</p>
                <p className="card-precio">${precio}</p>
            </div>
            <FrmInconsistencias />
        </div>
    );
};
