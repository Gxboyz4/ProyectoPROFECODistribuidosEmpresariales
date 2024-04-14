/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import conexion.Conexion;
import dominio.Inconsistencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author 
 */
public class InconsistenciasDAO implements IFachadaDAO{
    private final MongoDatabase BASE_DATOS;
    private final String NOMBRE_COLECCION = "supermercados";
    private MongoCollection<Inconsistencia> coleccion;

    public InconsistenciasDAO() {
        BASE_DATOS = Conexion.dameInstancia();
        coleccion = BASE_DATOS.getCollection(NOMBRE_COLECCION, Inconsistencia.class);
    }

    @Override
    public Inconsistencia registrarInconsistencia(Inconsistencia inconsistencia) {
        inconsistencia.generarID();
        coleccion.insertOne(inconsistencia);
        return inconsistencia;
    }
    @Override
    public Inconsistencia actualizarInconsistencia(Inconsistencia inconsistencia) {
        coleccion.replaceOne(eq("id", inconsistencia.getId()), inconsistencia);
        return inconsistencia;
    }

    @Override
    public Inconsistencia consultarInconsistencia(String id) {
        return coleccion.find(eq("_id", id)).first();
    }

    @Override
    public List<Inconsistencia> consultarInconsistenciasSupermercado(String idSupermercado) {
        List<Inconsistencia> listaInconsistencias = new ArrayList<>();
        coleccion.find(new Document("idSupermercado", idSupermercado)).into(listaInconsistencias);
        return listaInconsistencias;
    }

    @Override
    public List<Inconsistencia> consultarInconsistenciasProducto(String idProducto) {
        List<Inconsistencia> listaInconsistencias = new ArrayList<>();
    coleccion.find(new Document("idProducto", idProducto)).into(listaInconsistencias);
        return listaInconsistencias;
    }
    

}
