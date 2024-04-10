/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import org.bson.types.ObjectId;

/**
 *
 * @author julio
 */
public class Oferta {
    private String id;
    private String nombre;
    private String idSupermercado;
    private String nombreSupermercado;
    private String categoria;
    private double precio;
    private String direccionImagen;
    private double precioOferta;
    private String fechaInicio;
    private String fechaFinal;
    
    public void generarID(){
        ObjectId objectId = new ObjectId();
        this.id = objectId.toHexString();
    }

    public Oferta() {
    }

    public Oferta(String id, String nombre, String idSupermercado, String nombreSupermercado, String categoria, double precio, String direccionImagen, double precioOferta, String fechaInicio, String fechaFinal) {
        this.id = id;
        this.nombre = nombre;
        this.idSupermercado = idSupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.categoria = categoria;
        this.precio = precio;
        this.direccionImagen = direccionImagen;
        this.precioOferta = precioOferta;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public Oferta(String nombre, String idSupermercado, String nombreSupermercado, String categoria, double precio, String direccionImagen, double precioOferta, String fechaInicio, String fechaFinal) {
        this.nombre = nombre;
        this.idSupermercado = idSupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.categoria = categoria;
        this.precio = precio;
        this.direccionImagen = direccionImagen;
        this.precioOferta = precioOferta;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdSupermercado() {
        return idSupermercado;
    }

    public void setIdSupermercado(String idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public String getNombreSupermercado() {
        return nombreSupermercado;
    }

    public void setNombreSupermercado(String nombreSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "Oferta{" + "nombre=" + nombre + ", idSupermercado=" + idSupermercado + ", nombreSupermercado=" + nombreSupermercado + ", categoria=" + categoria + ", precio=" + precio + ", direccionImagen=" + direccionImagen + ", precioOferta=" + precioOferta + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + '}';
    }
    
    
}
