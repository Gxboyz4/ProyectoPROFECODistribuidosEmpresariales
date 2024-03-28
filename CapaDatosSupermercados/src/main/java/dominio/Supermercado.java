/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import org.bson.types.ObjectId;

/**
 *
 * @author 
 */
public class Supermercado {
    
    private ObjectId id;
    private String correo;
    private String contrasenia;
    private String nombre;
    private String direccion;

    public Supermercado() {
    }

    public Supermercado(ObjectId id, String correo, String contrasenia, String nombre, String direccion) {
        this.id = id;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Supermercado(String correo, String contrasenia, String nombre, String direccion) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Supermercado(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Supermercado(String correo) {
        this.correo = correo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",nombre,direccion);
    }
    
    
    
}
