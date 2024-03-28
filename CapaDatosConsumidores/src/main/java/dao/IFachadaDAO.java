/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import dominio.Consumidor;
import java.util.List;

/**
 *
 * @author 
 */
public interface IFachadaDAO {
    
    public Consumidor registrarConsumidor(Consumidor consumidor);
    
    public Consumidor actualizarConsumidor(Consumidor consumidor);
    
    public Consumidor consultarConsumidor(String correo);
    
    public Consumidor autenticar(String correo, String contrasenia);
    
}
