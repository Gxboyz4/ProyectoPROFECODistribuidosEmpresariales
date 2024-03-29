/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package itsos.org.gestorproductos;

import dao.FachadaDAO;
import dominio.Producto;
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
@Path("productos")
@RequestScoped
public class ProductosResource {

    @Context
    private UriInfo context;
    private FachadaDAO dao;

    /**
     * Creates a new instance of Productos
     */
    public ProductosResource() {
        dao = new FachadaDAO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarProducto(Producto producto) {
        if (producto != null && producto.getNombre() != null && producto.getIdSupermercado() != null) {
            Producto productoRegistrado = dao.registrarProducto(producto);
            return Response.ok().entity(productoRegistrado).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(Producto producto) {
        if (dao.actualizarProducto(producto) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity(producto).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("id") String id) {
        if (dao.eliminarProducto(id) == null) {
            return Response.status(404).build();
        } else {
            return Response.ok().entity("El producto fue eliminado con exito").build();
        }
    }

    @GET
    @Path("/{idSupermercado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarProductosIDSuper(@PathParam("idSupermercado") String id) {
        if (id != null) {
            if (!id.isBlank()) {
                List<Producto> listaProductos = dao.consultarProductosIdSuper(id);
                Producto[] productos = listaProductos.toArray(new Producto[listaProductos.size()]);
                if (productos != null) {
                    return Response.ok().entity(productos).build();
                } else {
                    return Response.status(404).build();
                }
            }
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarProductosFiltros(@QueryParam("nombreSuper") String nombreSuper,
            @QueryParam("nombreProducto") String nombreProducto,
            @QueryParam("categoria") String categoria
    ) {

        List<Producto> listaProductos = new ArrayList<>();
        Producto[] productos = null;
        if (nombreSuper != null) {
            if (!nombreSuper.isBlank()) {
                listaProductos = dao.consultarProductosNombreSuper(nombreSuper);
                productos = listaProductos.toArray(new Producto[listaProductos.size()]);

            }
        }

        if (nombreProducto != null) {
            if (!nombreProducto.isBlank()) {
                listaProductos = dao.consultarProductosNombre(nombreProducto);
                productos = listaProductos.toArray(new Producto[listaProductos.size()]);

            }
        }

        if (categoria != null) {
            if (!categoria.isBlank()) {
                listaProductos = dao.consultarProductoCategoria(categoria);
                productos = listaProductos.toArray(new Producto[listaProductos.size()]);

            }
        }
        if (productos != null) {
            if (productos.length > 0) {
                return Response.ok().entity(productos).build();
            }
        }

        return Response.status(404).build();
    }
}