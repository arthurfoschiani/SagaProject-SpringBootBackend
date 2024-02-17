package br.com.br.saga.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.br.saga.model.dto.Token;

@Service
public class TokenService {

    public Token generateToken(String email) {
        Algorithm algorithm = Algorithm.HMAC512("mysupersecret");
        String token = JWT.create()
            .withIssuer("sagaProject")
            .withSubject(email)
            .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
            .sign(algorithm);

        return new Token(token, "JWT", "Bearer");
    }
    
}
