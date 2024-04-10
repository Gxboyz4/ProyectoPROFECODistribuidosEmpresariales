import React, { useState, useEffect } from 'react';
import '../../estilos/estilosOfertas/ContenedorGestionOfertas.css';

export const ContenedorGestionOfertas = ({ estado, ofertaSeleccionada, onEdit}) => {
    const idSupermercado = estado.data.id;
    const nombreSupermercado = estado.data.nombre;
    const [nombre, setNombre] = useState('');
    const [categoria, setCategoria] = useState('');
    const [precio, setPrecio] = useState('');
    const [fechaInicio, setFechaInicio] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [precioOferta, setPrecioOferta] = useState('');
    const [error, setError] = useState('');
    const [mensajeExito, setExito] = useState('');

    const [ofertaActual, setOfertaActual] = useState(ofertaSeleccionada);

    useEffect(() => {
        setOfertaActual(ofertaSeleccionada);
    }, [ofertaSeleccionada]);

    useEffect(() => {
        if (ofertaActual) {
            setNombre(ofertaActual.nombre);
            setCategoria(ofertaActual.categoria);
            setPrecio(ofertaActual.precio);
            setFechaInicio(ofertaActual.fechaInicio)
            setFechaFin(ofertaActual.fechaFin);
            setPrecioOferta(ofertaActual.precioOferta);
        }
    }, [ofertaActual]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevaOferta = {
            nombre,
            idSupermercado,
            nombreSupermercado,
            categoria,
            precio: parseFloat(precio),
            precioOferta: parseFloat(precioOferta),
            fechaInicio,
            fechaFin
        };

        try {
            const response = await fetch('http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/ofertas/registrar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevaOferta)
            });

            if (response.ok) {
                onEdit(nuevaOferta);
                setExito("Oferta registrada");
                setNombre('');
                setCategoria('');
                setPrecio('');
            } else {
                const data = await response.json();
                setError(data.message); // Muestra el mensaje de error proporcionado por la API
            }
        } catch (error) {
            console.error('Error al registrar la oferta:', error);
            setError('Error al registrar oferta');
        }
    };

    const handleUpdate = async (e) => {
        e.preventDefault();
        // Implementación de la actualización de la oferta
        const ofertaActualizada = {
            id: ofertaActualizada.id,
            nombre,
            idSupermercado,
            nombreSupermercado,
            categoria,
            precio: parseFloat(precio),
            precioOferta: parseFloat(precioOferta),
            fechaInicio,
            fechaFin
        };

        try {
            const response = await fetch(`http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/ofertas/actualizar/`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(ofertaActualizada)
            });

            if (response.ok) {
                onEdit(ofertaActualizada);
                setOfertaActual(null);
                setExito("Oferta actualizada");
                setNombre('');
                setCategoria('');
                setPrecio('');
                setFechaInicio(null);
                setFechaFin(null);
                setPrecioOferta('');
            } else {
                const data = await response.json();
                setError(data.message);
            }
        } catch (error) {
            console.error('Error al actualizar la oferta:', error);
            setError('Error al actualizar la oferta');
        }
    };
    
    return (
        <div className="gestionofertas-form-container">
            <h2 className="titulo-gestionofertas">Gestión de ofertas</h2>
            <h3>Información producto</h3>
            <form onSubmit={ofertaActual ? handleUpdate : handleSubmit}>
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="text" placeholder="Categoría" value={categoria} onChange={(e) => setCategoria(e.target.value)} required />
                <input type="number" placeholder="Precio" value={precio} onChange={(e) => setPrecio(e.target.value)} required />
                <h3>Información oferta</h3>
                <input type="date" placeholder="Fecha de inicio" value={fechaInicio} onChange={(e) => setFechaInicio(e.target.value)} required />
                <input type="date" placeholder="Fecha de fin" value={fechaFin} onChange={(e) => setFechaFin(e.target.value)} required />
                <input type="number" placeholder="Precio de la oferta" value={precioOferta} onChange={(e) => setPrecioOferta(e.target.value)} required />
                <button type="submit" className="botonGestionofertas">{ofertaActual ? 'Actualizar' : 'Registrar'}</button>

            </form>
            {error && <label className="mensaje-error">{error}</label>}
            {mensajeExito && <label className="mensaje-exito">{mensajeExito}</label>}
        </div>
    );
};
