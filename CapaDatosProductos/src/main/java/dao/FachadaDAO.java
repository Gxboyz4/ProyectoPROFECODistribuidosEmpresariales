/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class FachadaDAO implements IFachadaDAO {

    private ProductosDAO productosDAO;

    public FachadaDAO() {
        this.productosDAO = new ProductosDAO();
    }

    @Override
    public Producto registrarProducto(Producto producto) {
        return productosDAO.registrarProducto(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productosDAO.actualizarProducto(producto);
    }

    @Override
    public List<Producto> consultarProductosIdSuper(String idSupermercado, int pagina, int cantidadMaxima) {
        return productosDAO.consultarProductosIdSuper(idSupermercado, pagina, cantidadMaxima);
    }

    @Override
    public List<Producto> consultarProductosNombreSuper(String nombreSupermercado, int pagina, int cantidadMaxima) {
        return productosDAO.consultarProductosNombreSuper(nombreSupermercado, pagina, cantidadMaxima);
    }

    @Override
    public List<Producto> consultarProductosNombre(String nombreProducto, int pagina, int cantidadMaxima) {
        return productosDAO.consultarProductosNombre(nombreProducto, pagina, cantidadMaxima);
    }

    @Override
    public List<Producto> consultarProductoCategoria(String categoria, int pagina, int cantidadMaxima) {
        return productosDAO.consultarProductoCategoria(categoria, pagina, cantidadMaxima);
    }
    
    @Override
    public List<Producto> consultarProductosFiltros(String nombreSuper, String nombreProducto, String categoria, int pagina, int cantidadMaxima) {
        return productosDAO.consultarProductosFiltros(nombreSuper, nombreProducto, categoria, pagina, cantidadMaxima);
    }
    
    @Override
    public List<Producto> consultarProductos(int pagina, int cantidadMaxima){
        return productosDAO.consultarProductos(pagina, cantidadMaxima);
    }
    
    @Override
    public DeleteResult eliminarProducto(String id) {
         return productosDAO.eliminarProducto(id);
    }

}
