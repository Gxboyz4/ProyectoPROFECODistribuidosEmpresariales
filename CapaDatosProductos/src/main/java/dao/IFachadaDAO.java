/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mongodb.client.result.DeleteResult;
import dominio.Producto;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author julio
 */
public interface IFachadaDAO {

    public Producto registrarProducto(Producto producto);

    public Producto actualizarProducto(Producto producto);

    public List<Producto> consultarProductosIdSuper(String idSupermercado);

    public List<Producto> consultarProductosNombreSuper(String nombreSupermercado);

    public List<Producto> consultarProductosNombre(String nombreProducto);

    public List<Producto> consultarProductoCategoria(String categoria);

    public DeleteResult eliminarProducto(String id);
}