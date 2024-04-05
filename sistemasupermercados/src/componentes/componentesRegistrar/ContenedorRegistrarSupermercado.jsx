import React, { useState } from 'react';
import "../../estilos/estilosRegistrar/ContenedorRegistrarSupermercado.css";
import { Link } from 'react-router-dom'; 
import { useNavigate } from 'react-router-dom';
import SweetAlert from '../SweetAlert'; // Importa el componente SweetAlert

export const ContenedorRegistrarSupermercado = () => {
    const [correo, setCorreo] = useState('');
    const [contrasenia, setContrasenia] = useState('');
    const [nombre, setNombre] = useState('');
    const [direccion, setDireccion] = useState('');
    const [error, setError] = useState('');
    const [showAlert, setShowAlert] = useState(false); // Estado para controlar la visibilidad del SweetAlert
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevoConsumidor = {
            correo,
            contrasenia,
            nombre,
            direccion
        };

        try {
            const response = await fetch('http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/supermercados/registrar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevoConsumidor)
            });

            if (response.ok) {
                setError('');
                setShowAlert(true);
            } else {
                const data = await response.json();
                setError(data.message); // Muestra el mensaje de error proporcionado por la API
            }
        } catch (error) {
            console.error('Error al registrar el supermercado:', error);
            setError('Error al registrar el supermercado');
        }
    };

    // Función para manejar la confirmación del SweetAlert y redireccionar
    const handleConfirmAlert = () => {
        navigate('/');
    };

    return (
        <div className="registro-form-container">
            <h2 className="titulo-registro">Registro de Supermercados</h2>
            <form onSubmit={handleSubmit}>
                <input type="email" placeholder="Correo electrónico" value={correo} onChange={(e) => setCorreo(e.target.value)} required />
                <input type="password" placeholder="Contraseña" value={contrasenia} onChange={(e) => setContrasenia(e.target.value)} required />
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="text" placeholder="Direccion" value={direccion} onChange={(e) => setDireccion(e.target.value)} required />
                <button type="submit" className="botonRegistrar">Registrarse</button>
                 {/* Enlace al registro */}
             <Link to="/" className="link-registro">¿Ya está registrado? Click aquí</Link>
            </form>
            {error && <label className="mensaje-error">{error}</label>}
            {showAlert && (
                <SweetAlert
                    message="¡Registro exitoso! ¿Desea navegar a la página de inicio?"
                    type="success"
                    onConfirm={handleConfirmAlert}
                    onCancel={() => setShowAlert(false)}
                />
            )}
        </div>
    );
};