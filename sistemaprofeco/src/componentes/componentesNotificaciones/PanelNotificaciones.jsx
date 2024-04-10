import React, { useState, useEffect } from 'react';
import '../estilos/Header.css';

export const NotificacionesPanel = () => {
  const [notificaciones, setNotificaciones] = useState([]);
  

    useEffect(() =>{

    })


    //PONER PRODUCTOS AQUI DE PARAMETRO    

    const desplegarNotificacion = (producto) =>{
        return(
            <span className='notificacion'> {`¡El producto ${producto} esta en oferta!`} </span>
        )
    }

  return (
    <div className="notificaciones-panel">
      <span className=''></span>
    </div>
  );
};