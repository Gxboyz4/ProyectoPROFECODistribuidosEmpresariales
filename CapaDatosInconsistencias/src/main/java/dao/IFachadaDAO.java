/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import dominio.Inconsistencia;
import java.util.List;

/**
 *
 * @author 
 */
public interface IFachadaDAO {
    
    public Inconsistencia registrarInconsistencia(Inconsistencia inconsistencia);
    
    public Inconsistencia actualizarInconsistencia(Inconsistencia inconsistencia);
    
    public Inconsistencia consultarInconsistencia(String id);
    
    public List<Inconsistencia> consultarInconsistenciasSupermercado(String idSupermercado);
    
    public List<Inconsistencia> consultarInconsistenciasProducto(String idProducto);
    
}
