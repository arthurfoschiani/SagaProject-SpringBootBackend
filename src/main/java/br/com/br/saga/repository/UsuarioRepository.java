package br.com.br.saga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.saga.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}