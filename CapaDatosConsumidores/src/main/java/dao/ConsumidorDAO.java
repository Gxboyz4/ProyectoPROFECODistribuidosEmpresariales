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
import dominio.Consumidor;
import conexion.Conexion;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author
 */
public class ConsumidorDAO implements IFachadaDAO {

    private final MongoDatabase BASE_DATOS;
    private final String NOMBRE_COLECCION = "consumidores";
    private MongoCollection<Consumidor> coleccion;

    public ConsumidorDAO() {
        BASE_DATOS = Conexion.dameInstancia();
        coleccion = BASE_DATOS.getCollection(NOMBRE_COLECCION, Consumidor.class);
    }

    @Override
    public Consumidor registrarConsumidor(Consumidor consumidor) {
        coleccion.insertOne(consumidor);
        return consumidor;
    }

    @Override
    public Consumidor actualizarConsumidor(Consumidor consumidor) {
        coleccion.replaceOne(eq("correo", consumidor.getCorreo()), consumidor);
        return consumidor;
    }

    @Override
    public Consumidor consultarConsumidor(String correo) {
        Bson filter = Filters.regex("correo", correo, "i");
        return coleccion.find(filter).first();
    }

    @Override
    public Consumidor autenticar(String correo, String contrasenia) {
        Bson filter = Filters.and(
            Filters.eq("correo", correo),
            Filters.eq("contrasenia", contrasenia)
        );
        
        return coleccion.find(filter).first();
    }

}