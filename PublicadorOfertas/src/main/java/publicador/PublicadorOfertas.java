
package publicador;

import dominio.Oferta;
import com.rabbitmq.client.*;
import utilerias.ConvertidorJSON;

public class PublicadorOfertas implements IPublicador{

    private static final String EXCHANGE_NAME = "ofertas";

    @Override
    public void publicarOferta(Oferta oferta) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String jsonOferta = ConvertidorJSON.convertirOfertaAJson(oferta);
            //channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.basicPublish(EXCHANGE_NAME, "", null, jsonOferta.getBytes("UTF-8"));
        }catch(Exception ex){
            System.out.println("Error al publicar oferta");
        }
    }
    
}
