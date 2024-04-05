import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import '../../estilos/estilosRegistroProducto/TablaProductos.css';

export const TablaProductos = ({ estado, onEdit  }) => {
  
  const idSupermercado = estado.data.id;
  const [data, setData] = useState([]);
  const [pagina, setPagina] = useState(1);
  const [paginaActual, setPaginaActual] = useState(1);

  const onDelete = (id) => {
    eliminarProducto(id);
  };

  const cargarProductos = async () => {
    try {
      const response = await fetch(`http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/consultarproductosidsuper/query?idSupermercado=${idSupermercado}&pagina=${pagina}`);
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

  const eliminarProducto = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/productos/eliminar/${id}`, {
          method: 'DELETE'
      });  
      if (response.ok) {
        console.log("Producto eliminado");
        cargarProductos(); // Llama a cargarProductos después de eliminar
      } else {
        console.error('Hubo un error al eliminar:', response.statusText);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    cargarProductos();
  }, [idSupermercado, pagina, paginaActual]);

  return (
    <div className="tabla-productos-container">
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

export default TablaProductos;
