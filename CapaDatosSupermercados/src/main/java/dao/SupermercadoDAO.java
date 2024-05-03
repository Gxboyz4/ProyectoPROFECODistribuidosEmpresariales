/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dominio.Supermercado;
import conexion.Conexion;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.itson.hashsha256.HashSHA256;

/**
 *
 * @author
 */
public class SupermercadoDAO implements IFachadaDAO {

    private final MongoDatabase BASE_DATOS;
    private final String NOMBRE_COLECCION = "supermercados";
    private MongoCollection<Supermercado> coleccion;

    public SupermercadoDAO() {
        BASE_DATOS = Conexion.dameInstancia();
        coleccion = BASE_DATOS.getCollection(NOMBRE_COLECCION, Supermercado.class);
    }

    @Override
    public Supermercado registrarSupermercado(Supermercado supermercado) {
        supermercado.generarID();
        supermercado.setContrasenia(HashSHA256.sha256(supermercado.getContrasenia()));
        coleccion.insertOne(supermercado);
        return supermercado;
    }

    @Override
    public Supermercado actualizarSupermercado(Supermercado supermercado) {
        coleccion.replaceOne(eq("correo", supermercado.getCorreo()), supermercado);
        return supermercado;
    }

    @Override
    public Supermercado consultarSupermercado(String correo) {
        Bson filter = Filters.regex("correo", correo, "i");
        return coleccion.find(filter).first();
    }

    @Override
    public List<Supermercado> consultarSupermercados(String nombre) {
        List<Supermercado> listaSupermercados = new ArrayList<>();
        coleccion.find(new Document("nombre", new Document("$regex", nombre).append("$options", "i"))).into(listaSupermercados);
        return listaSupermercados;
    }

    @Override
    public Supermercado autenticar(String correo, String contrasenia) {
        String passHasheada = HashSHA256.sha256(contrasenia);
        Bson filter = Filters.and(
            Filters.eq("correo", correo),
            Filters.eq("contrasenia", passHasheada)
        );
        return coleccion.find(filter).first();
    }

    @Override
    public List<Supermercado> consultarSupermercados() {
        List<Supermercado> listaSupermercados = new ArrayList<>();
        coleccion.find().into(listaSupermercados);
        return listaSupermercados;
    }

}