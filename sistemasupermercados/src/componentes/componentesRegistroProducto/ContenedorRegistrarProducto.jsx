import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom';
import '../../estilos/estilosRegistroProducto/ContenedorRegistrarProducto.css';

export const ContenedorRegistrarProducto = ({estado}) => {
    const idSupermercado = estado.data.id;
    const nombreSupermercado= estado.data.nombre;
    const [nombre, setNombre] = useState('');
    const [categoria, setCategoria] = useState('');
    const [direccionImagen, setDireccionImagen] = useState('');
    const [precio, setPrecio] = useState('');
    const [error, setError] = useState('');
    const [mensajeExito, setExito] = useState('');
    const handleImagenChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setDireccionImagen(file.name);
        }
    };
    
    // const history = useHistory();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevoProducto = {
            nombre,
            idSupermercado,
            nombreSupermercado,
            categoria,
            direccionImagen,
            precio: parseFloat(precio)
        };

        try {
            console.log(nuevoProducto);
            const response = await fetch('http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/registrar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevoProducto)
            });

            if (response.ok) {
                setExito("Producto registrado"); 
                setNombre('');
                setCategoria('');
                setDireccionImagen('');
                setPrecio('');
            } else {
                const data = await response.json();
                setError(data.message); // Muestra el mensaje de error proporcionado por la API
            }
        } catch (error) {
            console.error('Error al registrar el producto:', error);
            setError('Error al registrar el producto');
        }
    };
    return (
        <div className="registro-form-container">
            <h2 className="titulo-registro">Registro de Productos</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="text" placeholder="Categoria" value={categoria} onChange={(e) => setCategoria(e.target.value)} required />
                <input type="number" placeholder="Precio" value={precio} onChange={(e) => setPrecio(e.target.value)} required />
                <br/>
                <input type="file" placeholder="Imagen" accept=".png,.jpg" onChange={handleImagenChange} required />
                <button type="submit" className="botonRegistrar">Registrarse</button>
            </form>
            {error && <label className="mensaje-error">{error}</label>}
            {mensajeExito && <label className="mensaje-exito">{mensajeExito}</label>}
        </div>
    );
};