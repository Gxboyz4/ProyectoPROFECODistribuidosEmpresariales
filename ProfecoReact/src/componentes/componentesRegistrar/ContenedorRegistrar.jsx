import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom';
import { Link } from 'react-router-dom'; 
import "../../estilos/estilosRegistrar/ContenedorRegistrar.css";

export const ContenedorRegistrar = () => {
    const [correo, setCorreo] = useState('');
    const [contrasenia, setContrasenia] = useState('');
    const [nombre, setNombre] = useState('');
    const [apellidoPaterno, setApellidoPaterno] = useState('');
    const [apellidoMaterno, setApellidoMaterno] = useState('');
    const [estadoResidencia, setEstadoResidencia] = useState('');
    const [error, setError] = useState('');
   // const history = useHistory();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevoConsumidor = {
            correo,
            contrasenia,
            nombre,
            apellidoPaterno,
            apellidoMaterno,
            estadoResidencia
        };

        try {
            const response = await fetch('http://localhost:8080/APIGatewayConsumidores/resources/apiconsumidores/consumidores/registrar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevoConsumidor)
            });

            if (response.ok) {
                //history.push('/login'); // Redirige al usuario al inicio de sesión después de registrarse
            } else {
                const data = await response.json();
                setError(data.message); // Muestra el mensaje de error proporcionado por la API
            }
        } catch (error) {
            console.error('Error al registrar el consumidor:', error);
            setError('Error al registrar el consumidor');
        }
    };
    return (
        <div className="registro-form-container">
            <h2 className="titulo-registro">Registro de Consumidor</h2>
            <form onSubmit={handleSubmit}>
                <input type="email" placeholder="Correo electrónico" value={correo} onChange={(e) => setCorreo(e.target.value)} required />
                <input type="password" placeholder="Contraseña" value={contrasenia} onChange={(e) => setContrasenia(e.target.value)} required />
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="text" placeholder="Apellido Paterno" value={apellidoPaterno} onChange={(e) => setApellidoPaterno(e.target.value)} required />
                <input type="text" placeholder="Apellido Materno" value={apellidoMaterno} onChange={(e) => setApellidoMaterno(e.target.value)} required />
                <select value={estadoResidencia} onChange={(e) => setEstadoResidencia(e.target.value)} required>
                    <option value="" disabled>Selecciona estado de residencia</option>
                    <option value="Sonora">Sonora</option>
                    {/* Agregar más opciones de estado o leer de una lista */}
                </select>
                <button type="submit" className="botonRegistrar">Registrarse</button>
                 {/* Enlace al registro */}
             <Link to="/login" className="link-registro">¿Ya está registrado? Click aquí</Link>
            </form>
            {error && <label className="mensaje-error">{error}</label>}
        </div>
    );
};