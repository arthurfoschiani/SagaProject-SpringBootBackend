package br.com.br.saga.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.br.saga.model.CategoriaFilme;
import br.com.br.saga.model.Filme;
import br.com.br.saga.repository.CategoriaFilmeRepository;
import br.com.br.saga.repository.FilmeRepository;

@Configuration
@Profile("dev")
public class DatabaeSeeder implements CommandLineRunner {

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    CategoriaFilmeRepository categoriaFilmeRepository;

    private CategoriaFilme suspense = new CategoriaFilme(null, "Suspense");
    private CategoriaFilme romance = new CategoriaFilme(null, "Romance");
    private CategoriaFilme documentario = new CategoriaFilme(null, "Documentário");
    private CategoriaFilme aventura = new CategoriaFilme(null, "Aventura");
    private CategoriaFilme comedia = new CategoriaFilme(null, "Comédia");

    @Override
    public void run(String... args) throws Exception {
        categoriaFilmeRepository.saveAll(
                List.of(suspense, romance, documentario, aventura, comedia));

        filmeRepository.saveAll(
                List.of(
                        new Filme()
                                .withTitulo("Black Mirror")
                                .withSinopse(
                                        "Uma jornada enigmática pelo universo da tecnologia e suas consequências na sociedade moderna, explorando os limites entre o real e o virtual.")
                                .withDiretor("Charlie Brooker")
                                .withDuracao("1h00m")
                                .withDataEstreia(LocalDate.of(2011, 12, 4))
                                .withCaminhoBanner("BlackMirror.jpg")
                                .withFaixaEtaria(16)
                                .withCategoria(suspense),

                        new Filme()
                                .withTitulo("Bill Gates: Uma Mente Brilhante")
                                .withSinopse(
                                        "A incrível história de Bill Gates, desde sua infância até se tornar um dos homens mais ricos e influentes do mundo, mostrando sua visão única de negócios e filantropia.")
                                .withDiretor("Davis Guggenheim")
                                .withDuracao("2h00m")
                                .withDataEstreia(LocalDate.of(2019, 9, 20))
                                .withCaminhoBanner("BillGates.jpg")
                                .withFaixaEtaria(12)
                                .withCategoria(documentario),

                        new Filme()
                                .withTitulo("A Era dos Dados: O Impacto da Tecnologia na Sociedade")
                                .withSinopse(
                                        "Um olhar profundo e revelador sobre como a tecnologia e a coleta de dados estão moldando nosso mundo, destacando as oportunidades e desafios que enfrentamos nesta nova era digital.")
                                .withDiretor("Alex Garland")
                                .withDuracao("1h35m")
                                .withDataEstreia(LocalDate.of(2021, 5, 15))
                                .withCaminhoBanner("AEraDosDados.jpg")
                                .withFaixaEtaria(12)
                                .withCategoria(documentario),

                        new Filme()
                                .withTitulo("O Corpo Humano: Uma Jornada Interna")
                                .withSinopse(
                                        "Uma jornada fascinante pelos mistérios e maravilhas do corpo humano, explorando sua incrível capacidade de se adaptar e superar desafios.")
                                .withDiretor("David Attenborough")
                                .withDuracao("1h45m")
                                .withDataEstreia(LocalDate.of(2020, 10, 10))
                                .withCaminhoBanner("CorpoHumano.jpg")
                                .withFaixaEtaria(12)
                                .withCategoria(documentario),

                        new Filme()
                                .withTitulo("Brené Brown: O Poder da Vulnerabilidade")
                                .withSinopse(
                                        "Um retrato inspirador da vida e obra de Brené Brown, mostrando como suas pesquisas sobre vulnerabilidade e coragem transformaram a forma como entendemos a nós mesmos e nos relacionamos com os outros.")
                                .withDiretor("Oprah Winfrey")
                                .withDuracao("1h20m")
                                .withDataEstreia(LocalDate.of(2022, 2, 14))
                                .withCaminhoBanner("BrenéBrown.jpg")
                                .withFaixaEtaria(12)
                                .withCategoria(documentario)

                ));
    }
}
