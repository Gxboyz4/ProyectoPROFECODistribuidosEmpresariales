
package publicador;

import dominio.Inconsistencia;
import com.rabbitmq.client.*;
import utilerias.ConvertidorJSON;

public class PublicadorInconsistencias implements IPublicador{

    private static final String EXCHANGE_NAME = "inconsistencias";

    @Override
    public void publicarInconsistencia(Inconsistencia inconsistencia) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String jsonInconsistencia = ConvertidorJSON.convertirInconsistenciaAJson(inconsistencia);
            //channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.basicPublish(EXCHANGE_NAME, "", null, jsonInconsistencia.getBytes("UTF-8"));
        }catch(Exception ex){
            System.out.println("Error al publicar inconsistencia");
        }
    }
    
}
