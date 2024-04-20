/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.gestorinconsistencias;

import dao.FachadaDAO;
import dao.IFachadaDAO;
import dominio.Inconsistencia;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import publicador.IPublicador;
import publicador.PublicadorInconsistencias;

/**
 * REST Web Service
 *
 * @author 
 */
@Path("inconsistencias")
@RequestScoped
public class InconsistenciaResource {

    @Context
    private UriInfo context;
    private IFachadaDAO dao;
    private ConsumidorInconsistencias consumidor;
    public InconsistenciaResource() throws Exception {
        dao = new FachadaDAO();
        consumidor = new ConsumidorInconsistencias();
        consumidor.consumir();
    }

    //http://localhost:8080/GestorInconsistencias/resources/inconsistencias/'idSupermercado'
    @GET
    @Path("{idSupermercado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInconsistenciasSupermercado(@PathParam("idSupermercado") String idSupermercado) {
        List<Inconsistencia> listaInconsistencias = dao.consultarInconsistenciasSupermercado(idSupermercado);
        Inconsistencia[] inconsistencias = listaInconsistencias.toArray(new Inconsistencia[listaInconsistencias.size()]);
        if (inconsistencias != null) {
            if (inconsistencias.length > 0) {
                return Response.ok().entity(inconsistencias).build();
            }
        }
        return Response.status(404).build();
    }

//    //http://localhost:8080/GestorInconsistencias/resources/inconsistencias/'idProducto'
//    @GET
//    @Path("{idProducto}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response consultarInconsistenciasProducto(@PathParam("idProducto") String idProducto) {
//        List<Inconsistencia> listaInconsistencias = dao.consultarInconsistenciasProducto(idProducto);
//        Inconsistencia[] inconsistencias = listaInconsistencias.toArray(new Inconsistencia[listaInconsistencias.size()]);
//        if (inconsistencias != null) {
//            if (inconsistencias.length > 0) {
//                return Response.ok().entity(inconsistencias).build();
//            }
//        }
//        return Response.status(404).build();
//    }

    //http://localhost:8080/GestorInconsistencias/resources/inconsistencias/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarInconsistencia(Inconsistencia inconsistencia) {
        if (inconsistencia != null && dao.consultarInconsistencia(inconsistencia.getId()) == null) {
            
            Inconsistencia inconsistenciaRegistrado = dao.registrarInconsistencia(inconsistencia);
            return Response.ok().entity(inconsistenciaRegistrado).build();
        }
        return Response.status(404).build();
    }

    //http://localhost:8080/GestorInconsistencias/resources/inconsistencias/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarInconsistencia(Inconsistencia inconsistencia) {
        if (dao.actualizarInconsistencia(inconsistencia) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity(inconsistencia).build();
        }
    }
    
}

  
    
