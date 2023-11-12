package br.com.br.saga.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.br.saga.model.Filme;
import br.com.br.saga.model.dto.QuantidadePorCategoria;
import br.com.br.saga.repository.FilmeRepository;

@Service
public class CategoriaService {

    @Autowired
    FilmeRepository filmeRepository;

    public List<QuantidadePorCategoria> getContagemPorCategoria() {

        var filmes = filmeRepository.findAll();
    
        var contagens = filmes.stream()
            .collect(
                Collectors.groupingBy(
                    Filme::getCategoria,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .map(e -> new QuantidadePorCategoria(e.getKey().getNomeCategoria(), e.getValue()))
            .collect(Collectors.toList())
        ;
    
        return contagens;
    }
}
