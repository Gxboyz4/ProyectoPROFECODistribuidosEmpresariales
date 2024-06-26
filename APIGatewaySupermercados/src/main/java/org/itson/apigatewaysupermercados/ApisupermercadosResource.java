/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package org.itson.apigatewaysupermercados;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author
 */
@Path("apisupermercados")
public class ApisupermercadosResource {

    @POST
    @Path("/{servicio}/{metodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudPost(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo, String cuerpo,
            @Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo);
        if (url != null) {
            Response response = tokenJWT == null 
                    ? this.regresarInvocationBuilder(url).post(Entity.json(cuerpo))
                    : this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).post(Entity.json(cuerpo));
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @PUT
    @Path("/{servicio}/{metodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudPut(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo, String cuerpo,
            @Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo);
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).put(Entity.json(cuerpo));
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @GET
    @Path("/{servicio}/{metodo}/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudQuery(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo,
            @QueryParam("correo") String correo, @QueryParam("contrasenia") String contrasenia, @QueryParam("nombreSuper") String nombreSuper,
            @QueryParam("nombreProducto") String nombreProducto, @QueryParam("categoria") String categoria, @QueryParam("idSupermercado") String idSupermercado,
            @QueryParam("pagina") Integer pagina, @Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo);
        String filtroQuery = this.armarUrlQuery(correo, contrasenia, nombreSuper, nombreProducto, categoria, idSupermercado, pagina);
        if (filtroQuery != null) {
            if (url != null) {
                url += filtroQuery;
                Response response = tokenJWT == null
                    ? this.regresarInvocationBuilder(url).get()
                    : this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).get();
                return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    private String armarUrlQuery(String correo, String contrasenia, String nombreSuper, String nombreProducto, String categoria, String idSupermercado, Integer pagina) {
        if (pagina == null) {
            pagina = 1;
        }
        if (correo != null && contrasenia != null) {
            return "correo=" + correo + "&contrasenia=" + contrasenia;
        }
        if (idSupermercado != null) {
            return "idSupermercado=" + idSupermercado + "&pagina=" + pagina;
        }
        int parametrosAdjuntos = 0;
        String cadenaConsulta = "";
        if (nombreSuper != null) {
            cadenaConsulta += "nombreSuper=" + nombreSuper;
            parametrosAdjuntos++;
        }
        if (nombreProducto != null) {
            if (parametrosAdjuntos != 0) {
                cadenaConsulta += "&";
            } else {
                parametrosAdjuntos++;
            }
            cadenaConsulta += "nombreProducto=" + nombreProducto;
        }
        if (categoria != null) {
            if (parametrosAdjuntos != 0) {
                cadenaConsulta += "&";
            } else {
                parametrosAdjuntos++;
            }
            cadenaConsulta += "categoria=" + categoria;
        }
        cadenaConsulta += "&pagina=" + pagina;
        if (parametrosAdjuntos != 0) {
            return cadenaConsulta;
        } else {
            return null;
        }
    }

    @GET
    @Path("/{servicio}/{metodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudesGet(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo,
            @Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo);
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).get();
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @GET
    @Path("/{servicio}/{metodo}/{parametroBusqueda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudesGetPorParametro(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo,
            @PathParam("parametroBusqueda") String parametroBusqueda ,@Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo) + parametroBusqueda;
        if (url != null) {
            Response response = tokenJWT == null 
                    ? this.regresarInvocationBuilder(url).get()
                    :this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).get();
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    @DELETE
    @Path("/{servicio}/{metodo}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrutarSolicitudesDeletePorId(@PathParam("servicio") String servicio, @PathParam("metodo") String metodo,
            @PathParam("id") String id,@Context HttpHeaders headers) {
        String tokenJWT = headers.getHeaderString("Authorization");
        String url = this.obtenerUrl(servicio, metodo) + id;
        if (url != null) {
            Response response = this.regresarInvocationBuilder(url).header("Authorization", tokenJWT).delete();
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Servicio y/o método no reconocido: " + metodo).build();
    }

    public String obtenerUrl(String servicio, String metodo) {
        String url = "http://localhost:8080/";

        switch (servicio) {
            case "supermercados":
                url += "GestorSupermercados/resources/supermercados";
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
                    case "consultartodos":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "consultarnombre":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    default:
                        return null;
                }
                break;
            case "productos":
                url += "GestorDeProductos/resources/productos";
                switch (metodo) {
                    case "registrar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "actualizar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "eliminar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "consultarproductosidsuper":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/query?";
                        break;
                    case "consultarfiltros":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/query?";
                        break;
                    case "consultarpagina":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    default:
                        return null;
                }
                break;
            case "ofertas":
                url += "GestorOfertas/resources/ofertas";
                switch (metodo) {
                    case "registrar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "actualizar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "eliminar":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/";
                        break;
                    case "consultarofertasidsuper":
                        System.out.println("Metodo " + metodo + " del servicio " + servicio);
                        url += "/query?";
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

    private Invocation.Builder regresarInvocationBuilder(String url) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        return invocationBuilder;
    }
}
