/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorinconsistencias;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import dao.FachadaDAO;
import dao.IFachadaDAO;
import dominio.Inconsistencia;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ConsumidorInconsistencias {
    private static final String EXCHANGE_NAME = "inconsistencias";
    private static IFachadaDAO dao = new FachadaDAO();
    public ConsumidorInconsistencias() {
    }
    public static void main(String[] args) {
        
        try{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); 

        
            Connection connection = factory.newConnection(); Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
            
            String queueName = channel.queueDeclare().getQueue();
            
            
            channel.queueBind(queueName, EXCHANGE_NAME, "");
            System.out.println(" [*] Esperando mensajes. Para salir presione CTRL+C");
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Recibido '" + message + "'");

               
                Inconsistencia inconsistencia = null;
                try {
                    inconsistencia = ConvertidorJson.convertirStringAInconsistencia(message);
                } catch (Exception ex) {
                    Logger.getLogger(ConsumidorInconsistencias.class.getName()).log(Level.SEVERE, null, ex);
                }

                dao.registrarInconsistencia(inconsistencia);

            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    

}
