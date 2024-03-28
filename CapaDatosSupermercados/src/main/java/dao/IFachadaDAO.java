/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import dominio.Supermercado;
import java.util.List;

/**
 *
 * @author 
 */
public interface IFachadaDAO {
    
    public Supermercado registrarSupermercado(Supermercado supermercado);
    
    public Supermercado actualizarSupermercado(Supermercado supermercado);
    
    public Supermercado consultarSupermercado(String correo);
    
    public List<Supermercado> consultarSupermercados(String nombre);
    
    public List<Supermercado> consultarSupermercados();
    
    public Supermercado autenticar(String correo, String contrasenia);
    
}
