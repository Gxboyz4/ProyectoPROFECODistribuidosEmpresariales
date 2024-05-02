import React, { useState, useEffect } from 'react';
import '../estilos/Header.css';
import logoProfeco from '../img/logo-profeco.png';
import iconoNotificacion from '../img/iconos/icono_notificacion.png';
import iconoLogout from '../img/iconos/iconologout.png';
import { useAuth0 } from "@auth0/auth0-react";

import { Client } from '@stomp/stompjs'
export const Header = ({ onSearch }) => {
  const [nombreProducto, setNombreProducto] = useState('');
  const [mostrarNotificaciones, setMostrarNotificaciones] = useState(false);
  const [notificaciones, setNotificaciones] = useState([]);
  const { logout } = useAuth0();

  useEffect(() => {
    const client = new Client({
      brokerURL: 'ws://localhost:15674/ws',
      onConnect: () => {
        client.subscribe('/exchange/ofertas', message => {
          const mensaje = JSON.parse(message.body);
          
          if (!notificaciones.includes(mensaje.nombre)) {
            setNotificaciones(prevNotificaciones => [...prevNotificaciones, mensaje]);
          }
        });
      }
    });
    client.activate();

    return () => {
      client.deactivate(); 
    };
  }, []); 

  

  const manejarCambios = (event) => {
    setNombreProducto(event.target.value);
  };

  const manejarEnviar = () => {
    let valor = nombreProducto;
    let nuevaCadena = valor.replace(/ /g, ".");
    onSearch(nuevaCadena);
  };


  const desplegarNotificacion = (producto) => {
    return (
      <span className='notificacion'> {`¡Oferta ${producto.nombre}, de  ${producto.nombreSupermercado} a $${producto.precio} en $${producto.precioOferta}!`} </span>
    );
  }

  const manejadorLeido = () => {
    setNotificaciones([]);
    setMostrarNotificaciones(false);
  };

  return (
    <header className="header-container">

      <div className="logo-container">
        <img src={logoProfeco} alt="Profeco" className="logo" />
      </div>

      <div className="search-container">
        <input
          type="text"
          placeholder="Buscar..."
          className="search-input"
          value={nombreProducto}
          onChange={manejarCambios}
        />
        <button className="search-button" onClick={manejarEnviar}>Buscar</button>
      </div>

      <div className='iconos-container'>
        <div className='iconos-header' onClick={() => setMostrarNotificaciones(!mostrarNotificaciones)}>
          <img src={iconoNotificacion} alt="icono-notificacion" />
          {
            notificaciones.length > 0 &&
            <div className="contador-notificaciones">{notificaciones.length}</div>
          }
        </div>
        {mostrarNotificaciones && (
          <div className="notificaciones-panel">
            {notificaciones.length === 0 ? (
              <span className='notificacion'>No tienes ninguna notificación</span>
            ) : (
              <>
                {notificaciones.map((n) => desplegarNotificacion(n))}
                <button className="boton-leido" onClick={manejadorLeido}>
                  Marcar como leído
                </button>
              </>
            )}
          </div>
        )}
      </div>
      <div className='iconos-header' onClick={() => logout()}>
          <img src={iconoLogout} alt="icono-logout"/>
        </div>

    </header>
  );
};