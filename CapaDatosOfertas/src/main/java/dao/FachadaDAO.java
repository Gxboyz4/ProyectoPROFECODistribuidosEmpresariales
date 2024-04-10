/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.result.DeleteResult;
import dominio.Oferta;
import java.util.List;

/**
 *
 * @author julio
 */
public class FachadaDAO implements IFachadaDAO {

    private OfertasDAO ofertasDAO;

    public FachadaDAO() {
        this.ofertasDAO = new OfertasDAO();
    }

    @Override
    public Oferta registrarOferta(Oferta oferta) {
        return ofertasDAO.registrarOferta(oferta);
    }

    @Override
    public Oferta actualizarOferta(Oferta oferta) {

        return ofertasDAO.actualizarOferta(oferta);
    }

    @Override
    public List<Oferta> consultarOfertasIdSuper(String idSupermercado, int pagina, int cantidadMaxima) {
        return ofertasDAO.consultarOfertasIdSuper(idSupermercado, pagina, cantidadMaxima);

    }

    @Override
    public DeleteResult eliminarOferta(String id) {
        return ofertasDAO.eliminarOferta(id);

    }

}
