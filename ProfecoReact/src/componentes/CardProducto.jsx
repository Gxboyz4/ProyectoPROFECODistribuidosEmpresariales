import React from "react";
import "../estilos/CardProducto.css";

export const CardProducto = ({ nombre, nombreSupermercado, categoria, precio, imagenUrl }) => {
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
            <a href="https://www.google.com" className="card-btn">
                GODEL
            </a> 
        </div>
    );
};