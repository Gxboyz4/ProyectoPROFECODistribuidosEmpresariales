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

/**
 *
 * @author
 */
public class FachadaDAO implements IFachadaDAO {

    private SupermercadoDAO supermercadoDAO;

    public FachadaDAO() {
        this.supermercadoDAO = new SupermercadoDAO();
    }

    @Override
    public Supermercado registrarSupermercado(Supermercado supermercado) {
        return supermercadoDAO.registrarSupermercado(supermercado);
    }

    @Override
    public Supermercado actualizarSupermercado(Supermercado supermercado) {
        return supermercadoDAO.actualizarSupermercado(supermercado);
    }

    @Override
    public Supermercado consultarSupermercado(String correo) {
        return supermercadoDAO.consultarSupermercado(correo);
    }

    @Override
    public List<Supermercado> consultarSupermercados(String nombre) {
        return supermercadoDAO.consultarSupermercados(nombre);
    }

    @Override
    public Supermercado autenticar(String correo, String contrasenia) {
        return supermercadoDAO.autenticar(correo, contrasenia);
    }

    @Override
    public List<Supermercado> consultarSupermercados() {
        return supermercadoDAO.consultarSupermercados();
    }

}
