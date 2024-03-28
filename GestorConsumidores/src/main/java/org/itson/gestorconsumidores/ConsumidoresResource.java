/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package org.itson.gestorconsumidores;

import dao.FachadaDAO;
import dao.IFachadaDAO;
import dominio.Consumidor;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("consumidores")
public class ConsumidoresResource {

    @Context
    private UriInfo context;
    private IFachadaDAO dao;

    public ConsumidoresResource() {
        dao = new FachadaDAO();
    }

    //http://localhost:8080/GestorConsumidores/resources/consumidores/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarConsumidor(Consumidor consumidor) {
        if (consumidor != null && consumidor.getCorreo() != null && dao.consultarConsumidor(consumidor.getCorreo()) == null) {
            Consumidor consumidorRegistrar = dao.registrarConsumidor(consumidor);
            return Response.ok().entity(consumidorRegistrar).build();
        }
        return Response.status(404).build();
    }

    //http://localhost:8080/GestorConsumidores/resources/consumidores/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarConsumidor(Consumidor consumidor) {
        if (dao.actualizarConsumidor(consumidor) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity(consumidor).build();
        }
    }

    //http://localhost:8080/GestorConsumidores/resources/consumidores/query?correo='correo'&contrasenia='contrasenia'
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarConsumidor(
            @QueryParam("correo") String correo,
            @QueryParam("contrasenia") String contrasenia) {
        Consumidor consumidorAutenticar = dao.autenticar(correo, contrasenia);
        if (consumidorAutenticar != null) {
            return Response.ok().entity(consumidorAutenticar).build();
        }
        return Response.status(404).build();
    }
}
