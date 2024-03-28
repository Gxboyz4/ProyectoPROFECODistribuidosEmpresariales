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
public class Producto {

    private String id;
    private String nombre;
    private String idSupermercado;
    private String nombreSupermercado;
    private String categoria;
    private String direccionImagen;

    public Producto() {
    }

    public Producto(String id) {
        this.id = id;
    }

    public Producto(String id, String nombre, String idSupermercado, String nombreSupermercado, String categoria, String direccionImagen) {
        this.id = id;
        this.nombre = nombre;
        this.idSupermercado = idSupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.categoria = categoria;
        this.direccionImagen = direccionImagen;
    }

    public Producto(String nombre, String idSupermercado, String nombreSupermercado, String categoria, String direccionImagen) {
        this.nombre = nombre;
        this.idSupermercado = idSupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.categoria = categoria;
        this.direccionImagen = direccionImagen;
    }

    public Producto(String nombre, String idSupermercado, String nombreSupermercado, String categoria) {
        
        this.nombre = nombre;
        this.idSupermercado = idSupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.categoria = categoria;
    }
    
    public void generarID(){
        ObjectId objectId = new ObjectId();
        this.id = objectId.toHexString();
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

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    @Override
    public String toString() {
        return "Productos{" + "nombre=" + nombre + ", nombreSupermercado=" + nombreSupermercado + ", categoria=" + categoria + '}';
    }

}
