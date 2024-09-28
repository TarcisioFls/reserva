package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModel;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModelAtualizado;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class ListarTodosAvaliacaoControllerTesteIntegracao {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarTodosAvaliacaoController listarTodosAvaliacaoController;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private int pagina;
    private int tamanho;

    @BeforeEach
    void setUp() {
        avaliacaoRepository.deleteAll();
        avaliacaoRepository.save(getCriarAvaliacaoModel());
        avaliacaoRepository.save(getCriarAvaliacaoModelAtualizado());
        pagina = 0;
        tamanho = 10;
    }

    @AfterEach
    void tearDown() {
        avaliacaoRepository.deleteAll();
    }

    @Test
    void testeListarTodos() {

        var resultado = listarTodosAvaliacaoController.listarTodos(pagina, tamanho);

        assertEquals(200,resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());
        assertAll("Teste metadata",
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );
    }
}
