/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package org.itson.apigateway;

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
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author
 */
@Path("apiconsumidores")
public class ApiconsumidoresResource {

    @POST
    @Path("/{servicio}/{metodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudPost(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo, String cuerpo) {
        String url = this.obtenerUrl(servicio, metodo);
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).post(Entity.json(cuerpo));
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @PUT
    @Path("/{servicio}/{metodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudPut(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo, String cuerpo) {
        String url = this.obtenerUrl(servicio, metodo);
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).put(Entity.json(cuerpo));
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @GET
    @Path("/{servicio}/{metodo}/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudQuery(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo,
            @QueryParam("correo") String correo, @QueryParam("contrasenia") String contrasenia) {
        String url = this.obtenerUrl(servicio, metodo);
        url += "correo=" + correo + "&contrasenia=" + contrasenia;
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).get();

            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    private Invocation.Builder regresarInvocationBuilder(String url) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        return invocationBuilder;
    }

    private String obtenerUrl(String servicio, String metodo) {
        String url = "http://localhost:8080/";
        
        switch (servicio) {
            case "consumidores":
                url += "GestorConsumidores/resources/consumidores";
                switch (metodo) {
                    case "registrar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "autenticar":
                        url += "/query?";
                        break;
                    case "actualizar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    default:
                        return null;
                }
                break;
            case "inconsistencias":
                url += "PublicadorRetroalimentacion/resources/publicador";
                switch (metodo) {
                    case "publicar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/inconsistencia";
                        break;
                    default:
                        return null;
                }
                break;
            default:
                return null;
        }

        return url;
    }

}
