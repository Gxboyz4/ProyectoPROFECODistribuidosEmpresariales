import React, { useState } from 'react';
import '../estilos/Header.css';
import logoProfeco from '../img/logo-profeco.png';
import iconoNotificacion from '../img/iconos/icono_notificacion.png';

export const Header = ({ onSearch }) => {
  const [nombreProducto, setNombreProducto] = useState('');
  const [mostrarNotificaciones, setMostrarNotificaciones] = useState(false);
  const [notificaciones, setNotificaciones] = useState(['Pepsi', 'Jabón', 'Coca cola']);


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
      <span className='notificacion'> {`¡El producto ${producto} esta en oferta!`} </span>
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
        <div className='icono-notificacion' onClick={() => setMostrarNotificaciones(!mostrarNotificaciones)}>
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
      
    </header>
  );
};