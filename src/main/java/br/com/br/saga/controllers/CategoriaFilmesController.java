package br.com.br.saga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.br.saga.model.CategoriaFilme;
import br.com.br.saga.model.dto.QuantidadePorCategoria;
import br.com.br.saga.repository.CategoriaFilmeRepository;
import br.com.br.saga.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("categorias")
public class CategoriaFilmesController {

    @Autowired
    CategoriaFilmeRepository repository;

    @Autowired
    CategoriaService service;

    @GetMapping
    public List<CategoriaFilme> Listar() {
        log.info("Listando todos as categorias");
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<CategoriaFilme> Cadastrar(@RequestBody @Valid CategoriaFilme categoria) {
        log.info("cadastrando categoria - " + categoria);
        repository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaFilme> BuscarPorId(@PathVariable Long id) {
        log.info("mostrar categoria com id - " + id);

        return ResponseEntity.ok(getCategoria(id));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> Deletar(@PathVariable Long id) {
        log.info("apagando categoria com id - " + id);

        repository.delete(getCategoria(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaFilme> Atualizar (@PathVariable Long id, @RequestBody CategoriaFilme categoria) {
        log.info("atualizando categoria com id - " + id);

        getCategoria(id);

        categoria.setId(id);
        repository.save(categoria);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping("quantidade-filmes")
    public ResponseEntity<List<QuantidadePorCategoria>> quantidadePorCategoria () {
        var lista = service.getContagemPorCategoria();
        return ResponseEntity.ok(lista);
    }

    private CategoriaFilme getCategoria(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }
}