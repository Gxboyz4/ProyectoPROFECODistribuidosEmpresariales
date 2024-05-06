import React, { useState, useEffect } from 'react';
import '../../estilos/estilosRegistroProducto/ContenedorRegistrarProducto.css';

export const ContenedorRegistrarProducto = ({ estado, productoSeleccionado, onEdit }) => {
    const idSupermercado = estado.data.id;
    const nombreSupermercado = estado.data.nombre;
    const [nombre, setNombre] = useState('');
    const [categoria, setCategoria] = useState('');
    const [imagenFile, setImagenFile] = useState(null); // Almacena el archivo de imagen
    const [imagenFileName, setImagenFileName] = useState(''); // Almacena el nombre del archivo de imagen
    const [precio, setPrecio] = useState('');
    const [error, setError] = useState('');
    const [mensajeExito, setExito] = useState('');


    const [productoActual, setProductoActual] = useState(productoSeleccionado);

    const escribirImagenEnDirectorio = (imagenFile) => {
        //Escribir imagenFile que es un File extraido de un input html en el directorio
        //\ProyectoPROFECODistribuidosEmpresariales\imagenesProductos
    };

    const handleImagenChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setImagenFile(file);
            setImagenFileName(file.name);
        }
    };

    useEffect(() => {
        setProductoActual(productoSeleccionado);
    }, [productoSeleccionado]);

    useEffect(() => {
        if (productoActual) {
            setNombre(productoActual.nombre);
            setCategoria(productoActual.categoria);
            setImagenFileName(productoActual.direccionImagen); // Se establece el nombre del archivo de imagen
            setPrecio(productoActual.precio);
        }
    }, [productoActual]);

    const handleUpdate = async (e) => {
        e.preventDefault();
        // Implementación de la actualización del producto
        const productoActualizado = {
            id: productoActual.id,
            nombre,
            idSupermercado,
            nombreSupermercado,
            categoria,
            direccionImagen: imagenFileName, // Se envía el nombre del archivo en lugar del archivo en sí
            precio: parseFloat(precio)
        };

        try {
            const response = await fetch(`http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/actualizar/`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('token') 
                },
                body: JSON.stringify(productoActualizado)
            });

            if (response.ok) {
                onEdit(productoActualizado);
                setProductoActual(null);
                setExito("Producto actualizado");
                setNombre('');
                setCategoria('');
                setImagenFileName('');
                setPrecio('');
            } else {
                const data = await response.json();
                setError(data.message);
            }
        } catch (error) {
            console.error('Error al actualizar el producto:', error);
            setError('Error al actualizar el producto');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevoProducto = {
            nombre,
            idSupermercado,
            nombreSupermercado,
            categoria,
            direccionImagen: imagenFileName, // Se envía el nombre del archivo en lugar del archivo en sí
            precio: parseFloat(precio)
        };

        try {
            const response = await fetch('http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/registrar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('token') 
                },
                body: JSON.stringify(nuevoProducto)
            });

            if (response.ok) {
                onEdit(nuevoProducto);
                escribirImagenEnDirectorio(imagenFile);
                setExito("Producto registrado");
                setNombre('');
                setCategoria('');
                setImagenFileName('');
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
            <form onSubmit={productoActual ? handleUpdate : handleSubmit}>
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="text" placeholder="Categoría" value={categoria} onChange={(e) => setCategoria(e.target.value)} required />
                <input type="file" accept=".png,.jpg" onChange={handleImagenChange} />
                <input type="text" readOnly value={imagenFileName} required /> {/* Campo de texto no editable para mostrar el nombre del archivo */}
                <input type="number" placeholder="Precio" value={precio} onChange={(e) => setPrecio(e.target.value)} required />
                <button type="submit" className="botonRegistrar">{productoActual ? 'Actualizar' : 'Registrar'}</button>
            </form>
            {error && <label className="mensaje-error">{error}</label>}
            {mensajeExito && <label className="mensaje-exito">{mensajeExito}</label>}
        </div>
    );
};
