/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.publicadorretroalimentacion;

import dominio.Inconsistencia;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import publicador.IPublicador;
import publicador.PublicadorInconsistencias;

/**
 * REST Web Service
 *
 * @author Zaurus
 */
@Path("publicador")
@RequestScoped
public class PublicadorResource {

    private final IPublicador publicador = new PublicadorInconsistencias();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PublicadorResource
     */
    public PublicadorResource() {
    }

    //http://localhost:8080/PublicadorRetroalimentacion/resources/publicador/inconsistencia
    @POST
    @Path("/inconsistencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarInconsistencia(Inconsistencia inconsistencia) {
        publicador.publicarInconsistencia(inconsistencia);
        return Response.ok().entity(inconsistencia).build();
    }
}
