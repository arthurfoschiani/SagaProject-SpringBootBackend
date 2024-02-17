package br.com.br.saga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.saga.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);
    
}