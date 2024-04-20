/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorinconsistencias;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Inconsistencia;

/**
 *
 * @author 
 */
public class ConvertidorJson {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Inconsistencia convertirStringAInconsistencia(String jsonInconsistencia) throws Exception {
        return objectMapper.readValue(jsonInconsistencia, Inconsistencia.class);
    }
}
