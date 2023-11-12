package br.com.br.saga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.saga.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long>{

}