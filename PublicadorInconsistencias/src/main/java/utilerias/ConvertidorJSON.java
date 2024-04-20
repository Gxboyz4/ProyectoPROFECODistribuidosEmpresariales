/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Inconsistencia;

public class ConvertidorJSON {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertirInconsistenciaAJson(Inconsistencia inconsistencia) {
        try {
            return objectMapper.writeValueAsString(inconsistencia);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
