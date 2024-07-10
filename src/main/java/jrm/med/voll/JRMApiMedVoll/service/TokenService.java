package jrm.med.voll.JRMApiMedVoll.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jrm.med.voll.JRMApiMedVoll.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario){
        try{
            var algoritmo = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("API Voll.med") //Quem está gerando o token
                    .withSubject(usuario.getLogin())//Usuário que pegou esta key
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        DecodedJWT decodedJWT;
        try {
            var algoritmo = Algorithm.HMAC256(secret);
             return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                     .verify(tokenJWT)
                     .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido");
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
