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
    
    public List<Producto> consultarProductosIdSuper(String idSupermercado, int pagina, int cantidadMaxima);

    public List<Producto> consultarProductosNombreSuper(String nombreSupermercado, int pagina, int cantidadMaxima);

    public List<Producto> consultarProductosNombre(String nombreProducto, int pagina, int cantidadMaxima);

    public List<Producto> consultarProductoCategoria(String categoria, int pagina, int cantidadMaxima);

    public List<Producto> consultarProductosFiltros(String nombreSuper, String nombreProducto, String categoria, int pagina, int cantidadMaxima);
    
    public DeleteResult eliminarProducto(String id);

}
