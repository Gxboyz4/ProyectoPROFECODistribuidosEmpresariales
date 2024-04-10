/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package itsos.org.gestorofertas;

import dao.FachadaDAO;
import dominio.Oferta;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Web Service
 *
 * @author julio
 */
@Path("ofertas")
@RequestScoped
public class OfertasResource {

    @Context
    private UriInfo context;
    private FachadaDAO dao;
    private final int NUM_REGISTROS_POR_PAGINA = 10;

    /**
     * Creates a new instance of OfertasResource
     */
    public OfertasResource() {
          dao = new FachadaDAO();  
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarProducto(Oferta oferta) {
        if (oferta != null && oferta.getNombre() != null && oferta.getIdSupermercado() != null) {
            Oferta ofertaRegistrada = dao.registrarOferta(oferta);
            return Response.ok().entity(ofertaRegistrada).build();
        }
        return Response.status(404).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(Oferta oferta) {
        if (dao.actualizarOferta(oferta) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity(oferta).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("id") String id) {
        if (dao.eliminarOferta(id) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity("La oferta fue eliminado con exito").build();
        }
    }
    
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarProductosFiltros(
            @QueryParam("idSupermercado") String idSupermercado,
            @QueryParam("pagina") Integer pagina
    ) {
        if (pagina == null) {
            pagina = 1;
        }

        List<Oferta> listaOfertas = new ArrayList<>();
        Oferta[] ofertas = null;

        if (idSupermercado != null) {
            if (!idSupermercado.isBlank()) {
                listaOfertas = dao.consultarOfertasIdSuper(idSupermercado, pagina, NUM_REGISTROS_POR_PAGINA);
                ofertas = listaOfertas.toArray(new Oferta[listaOfertas.size()]);
                if (contieneRegistros(ofertas)) {
                    return Response.ok().entity(ofertas).build();
                }
                return Response.status(404).build();
            }
        }
        return Response.status(404).build();
    }
    private boolean contieneRegistros(Oferta[] ofertas) {
        if (ofertas != null) {
            if (ofertas.length > 0) {
                return true;
            }
        }
        return false;
    }
}
