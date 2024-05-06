import React, { useState } from 'react';
import "../../estilos/estilosLogin/ContenedorLogin.css";
import { Link } from 'react-router-dom'; 
import { useNavigate } from 'react-router-dom';

export const ContenedorLogin = () => {
    const [correo, setCorreo] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); 
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const response = await fetch(`http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/supermercados/autenticar/query?correo=${correo}&contrasenia=${password}`);
          if (response.ok) {
            const data = await response.json();
            localStorage.setItem('supermercado', JSON.stringify(data.supermercado));
            localStorage.setItem('token', data.token);
            setError('');
            navigate('/menu')
          } else {
            console.log('Error, usuario no encontrado:', response.status);
            setError('Usuario o contraseña incorrectos');
          }
        } catch (error) {
          console.log('Error al procesar la solicitud:', error);
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
           {/* Enlace al registro */}
           <Link to="/registrar" className="link-registro">Registrar</Link>
        </form>
      </div>
    )
}