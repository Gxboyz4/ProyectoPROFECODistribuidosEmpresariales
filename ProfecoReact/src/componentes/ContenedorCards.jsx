import React, { useState, useEffect } from 'react';
import { CardProducto } from "./CardProducto";
import '../estilos/ContenedorCards.css';

export const ContenedorCards = () => {
    const [productos, setProductos] = useState([]);

    useEffect(() => {
        const obtenerProductos = async () => {
            try {
                const response = await fetch('http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarfiltros/query?categoria=Snack&pagina=1');
                if (!response.ok) {
                    throw new Error('Error al obtener productos');
                }
                const data = await response.json();
                setProductos(data);
            } catch (error) {
                console.error('Error al obtener productos:', error);
            }
        };

        obtenerProductos();
    }, []);

    return (
        <div className="contenedor-cards">
            {productos.map(producto => (
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