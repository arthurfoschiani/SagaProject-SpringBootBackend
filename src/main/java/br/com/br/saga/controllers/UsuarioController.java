package br.com.br.saga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.br.saga.model.Usuario;
import br.com.br.saga.model.dto.Credenciais;
import br.com.br.saga.model.dto.Token;
import br.com.br.saga.model.dto.UsuarioResponse;
import br.com.br.saga.repository.UsuarioRepository;
import br.com.br.saga.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TokenService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder enconder;
    
    @PostMapping("/login")
    public ResponseEntity<Token> Login(@RequestBody Credenciais credenciais) {
        log.info("Requisição de login do: " + credenciais);
        authenticationManager.authenticate(credenciais.toAuthentication());
        return ResponseEntity.ok(service.generateToken(credenciais.email()));
    }

    @GetMapping("/usuarios")
    public List<Usuario> Listar() {
        log.info("Listando todos os usuários");
        return repository.findAll();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> Cadastrar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(enconder.encode(usuario.getSenha()));
        log.info("cadastrando usuario - " + usuario);
        repository.save(usuario);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(UsuarioResponse.fromUsuario(usuario));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> BuscarPorId(@PathVariable Long id) {
        log.info("mostrar usuario com id - " + id);

        return ResponseEntity.ok(getUsuario(id));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> Deletar(@PathVariable Long id) {
        log.info("apagando usuario com id - " + id);

        repository.delete(getUsuario(id));
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> Atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.info("atualizando usuario com id - " + id);

        getUsuario(id);

        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    private Usuario getUsuario(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }
}