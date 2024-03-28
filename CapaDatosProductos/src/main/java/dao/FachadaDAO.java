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
    public List<Producto> consultarProductosIdSuper(String idSupermercado) {
         return productosDAO.consultarProductosIdSuper(idSupermercado);
    }

    @Override
    public List<Producto> consultarProductosNombreSuper(String nombreSupermercado) {
         return productosDAO.consultarProductosNombreSuper(nombreSupermercado);
    }

    @Override
    public List<Producto> consultarProductosNombre(String nombreProducto) {
         return productosDAO.consultarProductosNombre(nombreProducto);
    }

    @Override
    public List<Producto> consultarProductoCategoria(String categoria) {
         return productosDAO.consultarProductoCategoria(categoria);
    }

    @Override
    public DeleteResult eliminarProducto(String id) {
         return productosDAO.eliminarProducto(id);
    }

}
