package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTAlgorithm {

    private String secretKey;

    public JWTAlgorithm() {
        secretKey = "jdD5d94lffHV59vAjdD5d94lffHV59vAzFGAO56VA2cV";
    }

    public String generarToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 604800000;

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Date expirationDate = claims.getBody().getExpiration();
            return !expirationDate.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
