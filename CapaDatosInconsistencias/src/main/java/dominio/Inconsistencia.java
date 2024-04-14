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
public class Inconsistencia {
    private String id;
    private String idSupermercado;
    private String idProducto;
    private String descripcion;

    public Inconsistencia() {
        generarID();
    }
    
    public Inconsistencia(String idSupermercado, String idProducto, String descripcion) {
        generarID();
        this.idSupermercado = idSupermercado;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
    }
    
    public Inconsistencia(String id, String idSupermercado, String idProducto, String descripcion) {
        this.id = id;
        this.idSupermercado = idSupermercado;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
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

    public String getIdSupermercado() {
        return idSupermercado;
    }

    public void setIdSupermercado(String idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Inconsistencia{" + "idSupermercado=" + idSupermercado + ", idProducto=" + idProducto + ", descripcion=" + descripcion + '}';
    }
    
}
