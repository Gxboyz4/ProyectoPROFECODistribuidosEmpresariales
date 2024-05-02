import React, { useState, useEffect } from 'react';
import { CardProducto } from "./CardProducto";
import "../../estilos/estilosBusquedaProductos/ContenedorCards.css";
import { useAuth0 } from "@auth0/auth0-react"

export const ContenedorCards = ({ filtros }) => {
    const [productos, setProductos] = useState([]);
    const [error, setError] = useState(null);
    const [token, setToken] = useState(null);
    const { isAuthenticated, getAccessTokenSilently } = useAuth0();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = await getAccessTokenSilently();
                setToken(token);
            } catch (error) {
            }
        };

        if (isAuthenticated) {
            fetchData();
        }
    }, [getAccessTokenSilently, isAuthenticated]);

    useEffect(() => {
        const obtenerProductos = async () => {
            try {
                let cadenaConsulta = "";
                let parametrosAdjuntos = 0;

                if (filtros.supermercado && filtros.supermercado !== "Todos") {
                    cadenaConsulta += `nombreSuper=${filtros.supermercado}`;
                    parametrosAdjuntos++;
                }

                if (filtros.nombreProducto) {
                    if (parametrosAdjuntos !== 0) {
                        cadenaConsulta += "&";
                    } else {
                        parametrosAdjuntos++;
                    }
                    cadenaConsulta += `nombreProducto=${filtros.nombreProducto}`;
                }

                if (filtros.categoria && filtros.categoria !== "Todos") {
                    cadenaConsulta += parametrosAdjuntos ? '&' : '';
                    cadenaConsulta += `categoria=${filtros.categoria}`;
                    parametrosAdjuntos++;
                }

                let urlPeticion = "";

                if (cadenaConsulta.trim() !== "") {
                    cadenaConsulta += `&pagina=1`;
                    const inicioUrl = "http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarfiltros/query";
                    urlPeticion = `${inicioUrl}?${cadenaConsulta}`;
                } else {
                    urlPeticion = "http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarpagina/1";
                }

                const response = await fetch(urlPeticion, {
                    headers: { 'Authorization': `Bearer ${token == null ? await getAccessTokenSilently() : token}` }
                });

                if (!response.ok) {
                    console.log(response.status);
                    throw new Error('No hay productos Disponibles');
                }
                const data = await response.json();
                setProductos(data);
                setError(null); // Limpiar el error si la respuesta es exitosa


            } catch (error) {
                console.error('Error al obtener productos:', error);
                setError('No hay productos Disponibles'); // Guardar el mensaje de error
                setProductos([]); // Establecer productos como un arreglo vacío
            }
        };

        obtenerProductos();
    }, [filtros]);

    return (
        <div className="contenedor-cards">
            {error && <div className="contenedor-cards-mensaje-error">{error}</div>}
            {productos.map(producto => (
                <CardProducto
                    key={producto.id}
                    idSupermercado={producto.idSupermercado}
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

