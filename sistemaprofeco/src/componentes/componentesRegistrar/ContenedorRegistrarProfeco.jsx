import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import "../../estilos/estilosRegistrar/ContenedorRegistrarProfeco.css";
import SweetAlert from '../SweetAlert'; // Importa el componente SweetAlert
import { useNavigate } from 'react-router-dom';

export const ContenedorRegistrarProfeco = () => {
    const [correo, setCorreo] = useState('');
    const [contrasenia, setContrasenia] = useState('');
    const [nombre, setNombre] = useState('');
    const [apellidoPaterno, setApellidoPaterno] = useState('');
    const [apellidoMaterno, setApellidoMaterno] = useState('');
    const [estadoResidencia, setEstadoResidencia] = useState('');
    const [error, setError] = useState('');
    const [showAlert, setShowAlert] = useState(false); // Estado para controlar la visibilidad del SweetAlert
    const navigate = useNavigate();

    useEffect(() => {
        if (error) {
            const timer = setTimeout(() => {
                setError('');
            }, 3000);

            return () => clearTimeout(timer);
        }
    }, [error]);

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
                setApellidoMaterno("")
                setApellidoPaterno("")
                setContrasenia("")
                setCorreo("")
                setEstadoResidencia("")
                setNombre("")

                setShowAlert(true); // Mostrar el SweetAlert antes del redireccionamiento
            } else {
                const data = await response.json();
                setError(data.message); // Muestra el mensaje de error proporcionado por la API
            }
        } catch (error) {
            console.error('Error al registrar el consumidor:', error);
            setError('Error al registrar el consumidor');
        }
    };

    // Función para manejar la confirmación del SweetAlert y redireccionar
    const handleConfirmAlert = () => {
        navigate('/');
    };

    return (
        
        <div className="registro-form-container">
            
            <h2 className="titulo-registro">Registro de Consumidor</h2>
            
            <form className='form' onSubmit={handleSubmit}>
            
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
                
                
            </form>
            <Link to="/" className="registro-link-registro">¿Ya está registrado? Click aquí</Link>
            
            {showAlert && (
                <SweetAlert
                    message="¡Registro exitoso! ¿Desea navegar a la página de inicio?"
                    type="success"
                    onConfirm={handleConfirmAlert}
                    onCancel={() => setShowAlert(false)}
                />
            )}
            {error && <label className="registro-mensaje-error">{error}</label>}
        </div>
    );
};