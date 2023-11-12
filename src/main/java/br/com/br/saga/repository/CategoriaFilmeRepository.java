package br.com.br.saga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.saga.model.CategoriaFilme;


public interface CategoriaFilmeRepository extends JpaRepository<CategoriaFilme, Long>{
    
}