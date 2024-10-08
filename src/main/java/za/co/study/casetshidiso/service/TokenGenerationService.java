package za.co.study.casetshidiso.service;

import io.jsonwebtoken.Jwts;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;


@ApplicationScoped
public class TokenGenerationService {

    public String generateToken(String emailAddress, UriInfo uriInfo) {
        Key key = generateKey(emailAddress);

        return Jwts.builder()
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setSubject(emailAddress)
                .setAudience(uriInfo.getBaseUri().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant()))
                .setId(UUID.randomUUID().toString())
                .signWith(key, HS256)
                .compact();
    }

    public Key generateKey(String keyString){
        return new SecretKeySpec(new Sha256Hash(keyString).getBytes(), HS256.getJcaName());
    }
}