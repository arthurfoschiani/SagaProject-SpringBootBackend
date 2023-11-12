package br.com.br.saga.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.saga.model.Filme;


public interface FilmeRepository extends JpaRepository<Filme, Long>{

    Page<Filme> findByTituloContainingIgnoreCase(String titulo, Pageable pageRequest);

    // @Query("SELECT f FROM Filme f ORDER BY f.id LIMIT ?2 OFFSET ?1")
    // List<Filme> findAll(int offset, int size);

}