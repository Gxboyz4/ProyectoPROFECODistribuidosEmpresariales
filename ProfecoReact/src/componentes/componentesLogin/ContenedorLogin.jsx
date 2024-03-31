import React, { useState } from 'react';
import "../../estilos/estilosLogin/ContenedorLogin.css";

export const ContenedorLogin = () => {
    const [correo, setCorreo] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); 

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const response = await fetch(`http://localhost:8080/APIGatewayConsumidores/resources/apiconsumidores/consumidores/autenticar/query?correo=${correo}&contrasenia=${password}`);
          if (response.ok) {
            const data = await response.json();
            console.log('Respuesta del servidor:', data);
            setError('');

          } else {
            console.error('Error, usuario no encontrado:', response.status);
            setError('Usuario o contraseña incorrectos');
          }
        } catch (error) {
          console.error('Error al procesar la solicitud:', error);
          setError('Error al procesar la solicitud');
        }
      };
      
    return (
        <div className="login-form-container">
        <h2 className="titulo-login">Iniciar sesión</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <input
              type="email"
              placeholder="Nombre de usuario"
              value={correo}
              onChange={(e) => setCorreo(e.target.value)}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              placeholder="Contraseña"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          {error && <label className="mensaje-error">{error}</label>}
          <button className="botonIniciar" type="submit">Iniciar sesión</button>
        </form>
      </div>
    )
}