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
public class FachadaDAO implements IFachadaDAO {

    private ConsumidorDAO consumidorDAO;

    public FachadaDAO() {
        this.consumidorDAO = new ConsumidorDAO();
    }

    @Override
    public Consumidor registrarConsumidor(Consumidor consumidor) {
        return consumidorDAO.registrarConsumidor(consumidor);
    }

    @Override
    public Consumidor actualizarConsumidor(Consumidor consumidor) {
        return consumidorDAO.actualizarConsumidor(consumidor);
    }

    @Override
    public Consumidor consultarConsumidor(String correo) {
        return consumidorDAO.consultarConsumidor(correo);
    }

    @Override
    public Consumidor autenticar(String correo, String contrasenia) {
        return consumidorDAO.autenticar(correo, contrasenia);
    }


}
