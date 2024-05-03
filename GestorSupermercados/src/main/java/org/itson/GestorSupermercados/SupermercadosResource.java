/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package org.itson.GestorSupermercados;

import dao.FachadaDAO;
import dao.IFachadaDAO;
import dominio.Supermercado;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service
 *
 * @author
 */
@Path("supermercados")
public class SupermercadosResource {

    @Context
    private UriInfo context;
    private IFachadaDAO dao;

    public SupermercadosResource() {
        dao = new FachadaDAO();
    }

    //http://localhost:8080/GestorSupermercados/resources/supermercados/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarSupermercados() {
        List<Supermercado> listaSupermercados = dao.consultarSupermercados();
        Supermercado[] supermercados = listaSupermercados.toArray(new Supermercado[listaSupermercados.size()]);
        if (supermercados != null) {
            if (supermercados.length > 0) {
                return Response.ok().entity(supermercados).build();
            }
        }
        return Response.status(404).build();
    }

    //http://localhost:8080/GestorSupermercados/resources/supermercados/'nombre'
    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarSupermercados(@PathParam("nombre") String nombreSupermercado) {
        List<Supermercado> listaSupermercados = dao.consultarSupermercados(nombreSupermercado);
        Supermercado[] supermercados = listaSupermercados.toArray(new Supermercado[listaSupermercados.size()]);
        if (supermercados != null) {
            if (supermercados.length > 0) {
                return Response.ok().entity(supermercados).build();
            }
        }
        return Response.status(404).build();
    }

    //http://localhost:8080/GestorSupermercados/resources/supermercados/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarSupermercado(Supermercado supermercado) {
        if (supermercado != null && supermercado.getCorreo() != null && dao.consultarSupermercado(supermercado.getCorreo()) == null) {
            Supermercado supermercadoRegistrado = dao.registrarSupermercado(supermercado);
            return Response.ok().entity(supermercadoRegistrado).build();
        }
        return Response.status(404).build();
    }

    //http://localhost:8080/GestorSupermercados/resources/supermercados/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarSupermercado(Supermercado supermercado) {
        if (dao.actualizarSupermercado(supermercado) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity(supermercado).build();
        }
    }

    //http://localhost:8080/GestorSupermercados/resources/supermercados/query?correo='correo'&contrasenia='contrasenia'
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarSupermercado(
            @QueryParam("correo") String correo,
            @QueryParam("contrasenia") String contrasenia) {
        Supermercado supermercadoAutenticar = dao.autenticar(correo, contrasenia);
        if (supermercadoAutenticar != null) {
            return Response.ok().entity(supermercadoAutenticar).build();
        }
        return Response.status(404).build();
    }
    
}
