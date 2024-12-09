package com.apicontroladmin.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt_secret}")
    private String secret;

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("System@123");
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("sistema-computadores")
                    .build()
                    .verify(token);

            return jwt.getClaim("nomeUsuario").asString();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

}
