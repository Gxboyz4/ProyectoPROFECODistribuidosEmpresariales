import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import '../../estilos/estilosOfertas/Tablas.css';

export const TablaProductos = ({ estado, onEdit, productoActualizado }) => {

  const idSupermercado = estado.data.id;
  const [data, setData] = useState([]);
  const [pagina, setPagina] = useState(1);
  const [paginaActual, setPaginaActual] = useState(1);


  useEffect(() => {
    cargarProductos();
  }, [productoActualizado]);

  const cargarProductos = async () => {
    try {
      const response = await fetch(`http://localhost:8081/APIGatewaySupermercados-1/resources/apisupermercados/productos/consultarproductosidsuper/query?idSupermercado=${idSupermercado}&pagina=${pagina}`,{
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      });
      if (response.ok) {
        const data = await response.json();
        setData(data);
        setPaginaActual(pagina);
      } else {
        //console.error('Hubo un error al cargar los productos:', response.statusText);
        setPagina(paginaActual);
      }
    } catch (error) {
      setPagina(paginaActual);
    }
  };

  useEffect(() => {
    cargarProductos();
  }, [idSupermercado, pagina, paginaActual]);

  return (
    <div className="tabla-container">
      <h2 className="titulo-tabla">Lista de Productos</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Dirección de la Imagen</th>
            <th>Precio</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              <td>{item.nombre}</td>
              <td>{item.categoria}</td>
              <td>{item.direccionImagen}</td>
              <td>{item.precio}</td>
              <td className="acciones">
                <Button variant="primary" onClick={() => onEdit(item)}>Agregar oferta</Button>
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

export default TablaProductos;
