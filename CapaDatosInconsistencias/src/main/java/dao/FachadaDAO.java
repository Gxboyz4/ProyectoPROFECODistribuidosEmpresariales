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
public class FachadaDAO implements IFachadaDAO {

    private InconsistenciasDAO inconsistenciasDAO;

    public FachadaDAO() {
        this.inconsistenciasDAO = new InconsistenciasDAO();
    }

    @Override
    public Inconsistencia registrarInconsistencia(Inconsistencia inconsistencia) {
        return inconsistenciasDAO.registrarInconsistencia(inconsistencia);
    }

    @Override
    public Inconsistencia actualizarInconsistencia(Inconsistencia inconsistencia) {
        return inconsistenciasDAO.actualizarInconsistencia(inconsistencia);
    }

    @Override
    public Inconsistencia consultarInconsistencia(String id) {
        return inconsistenciasDAO.consultarInconsistencia(id);
    }

    @Override
    public List<Inconsistencia> consultarInconsistenciasSupermercado(String idSupermercado) {
        return inconsistenciasDAO.consultarInconsistenciasSupermercado(idSupermercado);
    }

    @Override
    public List<Inconsistencia> consultarInconsistenciasProducto(String idProducto) {
        return inconsistenciasDAO.consultarInconsistenciasProducto(idProducto);
    }

}
