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
import dominio.Oferta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class OfertasDAO implements IFachadaDAO{
    
    private final MongoDatabase BASE_DATOS;
    private final String NOMBRE_COLECCION = "oferta";
    private MongoCollection<Oferta> coleccion;

    public OfertasDAO() {
        BASE_DATOS = Conexion.dameInstancia();
        coleccion = BASE_DATOS.getCollection(NOMBRE_COLECCION, Oferta.class);
    }
    
    @Override
    public Oferta registrarOferta(Oferta oferta) {
        oferta.generarID();
        coleccion.insertOne(oferta);
        return oferta;
    }
    
    @Override
     public Oferta actualizarOferta(Oferta oferta) {
       coleccion.replaceOne(eq("_id", oferta.getId()), oferta);
        return oferta;
    }
    
    @Override
    public List<Oferta> consultarOfertasIdSuper(String idSupermercado, int pagina, int cantidadMaxima) {
        List<Oferta> listaOfertas = new ArrayList<>();

        coleccion.find(eq("idSupermercado", idSupermercado))
                .skip((pagina - 1) * cantidadMaxima)
                .limit(cantidadMaxima)
                .into(listaOfertas);

        return listaOfertas;
    }
    
    
    
    @Override
    public DeleteResult eliminarOferta(String id) {
        return coleccion.deleteOne(eq("_id", id));
    }
}
