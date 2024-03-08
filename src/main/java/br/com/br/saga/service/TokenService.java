package br.com.br.saga.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.br.saga.model.Usuario;
import br.com.br.saga.model.dto.Token;
import br.com.br.saga.repository.UsuarioRepository;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository repository;

    public Token generateToken(String email) {
        Algorithm algorithm = Algorithm.HMAC512("mysupersecret");
        String token = JWT.create()
            .withIssuer("sagaProject")
            .withSubject(email)
            .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
            .sign(algorithm);

        return new Token(token, "JWT", "Bearer");
    }

    public Usuario validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512("mysupersecret");
        String email = JWT.require(algorithm)
            .withIssuer("sagaProject")
            .build()
            .verify(token)
            .getSubject();

        return repository
            .findByEmail(email)
            .orElseThrow(() -> new JWTVerificationException("Erro na verificação do Token"));
    }
    
}
