import React, { useState, useEffect } from 'react';
import { CardProducto } from "./CardProducto";
import '../estilos/ContenedorCards.css';

export const ContenedorCards = ({ filtros }) => {
    const [productos, setProductos] = useState([]);

    useEffect(() => {
        const obtenerProductos = async () => {
            try {
                let cadenaConsulta = "";
                let parametrosAdjuntos = 0;

                if (filtros.supermercado) {
                    cadenaConsulta += `nombreSuper=${filtros.supermercado}`;
                    parametrosAdjuntos++;
                }
                
                if (filtros.categoria) {
                    cadenaConsulta += parametrosAdjuntos ? '&' : '';
                    cadenaConsulta += `categoria=${filtros.categoria}`;
                    parametrosAdjuntos++;
                }

                let urlPeticion = "";
                
                if(cadenaConsulta.trim() !== ""){
                    cadenaConsulta += `&pagina=1`;
                    const inicioUrl = "http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarfiltros/query";
                    urlPeticion = `${inicioUrl}?${cadenaConsulta}`;
                }else{
                    urlPeticion = "http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarpagina/1";
                }
                
                const response = await fetch(urlPeticion);
                if (!response.ok) {
                    throw new Error('Error al obtener productos');
                }
                const data = await response.json();
                setProductos(data);
            } catch (error) {
                console.error('Error al obtener productos:', error);
                setProductos([]);
            }
        };

        obtenerProductos();
        console.log("Filtros:", filtros);
    }, [filtros]);

    return (
        <div className="contenedor-cards">
            {productos.length > 0 && productos.map(producto => (
                <CardProducto
                    key={producto.id}
                    nombre={producto.nombre}
                    nombreSupermercado={producto.nombreSupermercado}
                    categoria={producto.categoria}
                    precio={producto.precio}
                    imagenUrl={producto.imagenUrl}
                />
            ))}
        </div>
    );
};
