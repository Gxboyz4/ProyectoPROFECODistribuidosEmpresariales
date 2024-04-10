/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mongodb.client.result.DeleteResult;
import dominio.Oferta;
import java.util.List;

/**
 *
 * @author julio
 */
public interface IFachadaDAO {

    public Oferta registrarOferta(Oferta oferta);

    public Oferta actualizarOferta(Oferta oferta);

    public List<Oferta> consultarOfertasIdSuper(String idSupermercado, int pagina, int cantidadMaxima);

    public DeleteResult eliminarOferta(String id);

}
