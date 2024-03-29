/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import conexion.Conexion;
import dominio.Producto;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author julio
 */
public class ProductosDAO implements IFachadaDAO{

    private final MongoDatabase BASE_DATOS;
    private final String NOMBRE_COLECCION = "prodcutos";
    private MongoCollection<Producto> coleccion;

    public ProductosDAO() {
        BASE_DATOS = Conexion.dameInstancia();
        coleccion = BASE_DATOS.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    @Override
    public Producto registrarProducto(Producto producto) {
        producto.generarID();
        coleccion.insertOne(producto);
        return producto;
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        System.out.println("cuantos fueron remplazados" + coleccion.replaceOne(eq("_id", producto.getId()), producto));
        return producto;
    }

    @Override
public List<Producto> consultarProductosIdSuper(String idSupermercado, int pagina, int cantidadMaxima) {
    List<Producto> listaProductos = new ArrayList<>();

    coleccion.find(eq("idSupermercado", idSupermercado))
             .skip((pagina - 1) * cantidadMaxima)
             .limit(cantidadMaxima)
             .into(listaProductos);

    return listaProductos;
}

@Override
public List<Producto> consultarProductosNombreSuper(String nombreSupermercado, int pagina, int cantidadMaxima) {
    List<Producto> listaProductos = new ArrayList<>();

    Document filtro = new Document("nombreSupermercado",
            new Document("$regex", nombreSupermercado)
                    .append("$options", "i"));

    coleccion.find(filtro)
             .skip((pagina - 1) * cantidadMaxima)
             .limit(cantidadMaxima)
             .into(listaProductos);

    return listaProductos;
}

@Override
public List<Producto> consultarProductosNombre(String nombreProducto, int pagina, int cantidadMaxima) {
    List<Producto> listaProductos = new ArrayList<>();

    Document filtro = new Document("nombre",
            new Document("$regex", nombreProducto)
                    .append("$options", "i"));

    coleccion.find(filtro)
             .skip((pagina - 1) * cantidadMaxima)
             .limit(cantidadMaxima)
             .into(listaProductos);

    return listaProductos;
}

@Override
public List<Producto> consultarProductoCategoria(String categoria, int pagina, int cantidadMaxima) {
    List<Producto> listaProductos = new ArrayList<>();

    Document filtro = new Document("categoria",
            new Document("$regex", categoria)
                    .append("$options", "i"));

    coleccion.find(filtro)
             .skip((pagina - 1) * cantidadMaxima)
             .limit(cantidadMaxima)
             .into(listaProductos);

    return listaProductos;
}
    
    @Override
    public DeleteResult eliminarProducto(String id){
        return coleccion.deleteOne(eq("_id", id));
    }
}
