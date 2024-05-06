/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validador;

import jwt.JWTAlgorithm;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ValidadorTokens {

    private final String uriApi = "https://dev-k6ciyg872mcyz0mm.us.auth0.com/userinfo";
    private JWTAlgorithm jwt;

    public ValidadorTokens() {
        this.jwt = new JWTAlgorithm();
    }

    public boolean validarToken(String authHeader) {
        if (authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(uriApi);
                    httpGet.setHeader("Authorization", "Bearer " + token);

                    HttpResponse httpResponse = null;
                    httpResponse = client.execute(httpGet);
                    int statusCode = httpResponse.getStatusLine().getStatusCode();

                return statusCode == 200;
            } catch (Exception e) {
                return false;
            }
        } else {
            return jwt.validarToken(authHeader);
        }
    }

}
