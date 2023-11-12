package br.com.br.saga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.br.saga.model.Filme;
import br.com.br.saga.repository.FilmeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FilmeController {

    @Autowired
    FilmeRepository repository;

    @GetMapping("/filmes")
    public Page<Filme> Listar(@PageableDefault(size = 6, sort = "dataEstreia", direction = Sort.Direction.DESC) Pageable pageRequest, @RequestParam(required = false) String titulo) {
        log.info("Listando todos os filems com params: " + pageRequest);
        if (titulo == null || titulo.isEmpty()) 
            return repository.findAll(pageRequest);

        return repository.findByTituloContainingIgnoreCase(titulo, pageRequest);
    }

    @PostMapping("/filmes")
    public ResponseEntity<Object> Criar(@RequestBody @Valid Filme filme) {
        log.info("cadastrando filme - " + filme);
        repository.save(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    @GetMapping("/filmes/{id}")
    public ResponseEntity<Filme> BuscarPorId(@PathVariable Long id) {
        log.info("mostrar filme com id - " + id);

        return ResponseEntity.ok(getFilme(id));
    }

    @DeleteMapping("/filmes/{id}")
    public ResponseEntity<Object> Deletar(@PathVariable Long id) {
        log.info("apagando filme com id - " + id);
        
        repository.delete(getFilme(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/filmes/{id}")
    public ResponseEntity<Filme> Atualizar(@PathVariable Long id, @RequestBody Filme filme) {
        log.info("atualizando filme com id - " + id);
        
        getFilme(id);

        filme.setId(id);
        repository.save(filme);

        return ResponseEntity.ok(filme);
    }

    private Filme getFilme(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }
}