import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import '../../estilos/estilosOfertas/Tablas.css';

export const TablaOfertas = ({ estado, onEdit, ofertaActualizada }) => {

  const idSupermercado = estado.data.id;
  const [data, setData] = useState([]);
  const [pagina, setPagina] = useState(1);
  const [paginaActual, setPaginaActual] = useState(1);

  const onDelete = (id) => {
  eliminarOferta(id);
  };

  const eliminarOferta= async (id) => {
    try {
      const response = await fetch(`http://localhost:8081/APIGatewaySupermercados-1/resources/apisupermercados/ofertas/eliminar/${id}`, {
        method: 'DELETE',
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      });
      if (response.ok) {
        console.log("Oferta eliminada");
        cargarOfertas(); //
      } else {
        console.error('Hubo un error al eliminar:', response.statusText);
      }
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    cargarOfertas();
  }, [ofertaActualizada]);

  const cargarOfertas = async () => {
    try {
      const response = await fetch(`http://localhost:8081/APIGatewaySupermercados-1/resources/apisupermercados/ofertas/consultarofertasidsuper/query?idSupermercado=${idSupermercado}&pagina=${pagina}`,{
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      });
      if (response.ok) {
        const data = await response.json();
        setData(data);
        setPaginaActual(pagina);
      } else {
        //console.error('Hubo un error al cargar las ofertas:', response.statusText);
        setPagina(paginaActual);
      }
    } catch (error) {
      setPagina(paginaActual);
    }
  };

  useEffect(() => {
    cargarOfertas();
  }, [idSupermercado, pagina, paginaActual]);

  
  return (
    <div className="tabla-container">
      <h2 className="titulo-tabla">Lista de ofertas</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>FechaInicio</th>
            <th>FechaFinal</th>
            <th>PrecioOferta</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              <td>{item.nombre}</td>
              <td>{item.categoria}</td>
              <td>{item.precio}</td>
              <td>{item.fechaInicio}</td>
              <td>{item.fechaFinal}</td>
              <td>{item.precioOferta}</td>
              <td className="acciones">
                <Button variant="primary" onClick={() => onEdit(item)}>Editar</Button>
                <Button variant="danger" onClick={() => onDelete(item.id)}>Eliminar</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <div className="paginacion">
        <Button onClick={() => setPagina(pagina - 1)} disabled={pagina === 1}>Página anterior</Button>
        <Button onClick={() => setPagina(pagina + 1)}>Página siguiente</Button>
      </div>
    </div>
  );
};

export default TablaOfertas;
