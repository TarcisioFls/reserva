package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados;
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

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class ListarTodosProprietarioControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarTodosProprietarioController listarTodosProprietarioController;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @BeforeEach
    void setUp() {
        proprietarioRepository.deleteAll();
        proprietarioRepository.save(ProprietarioDados.getProprietarioModel());
        proprietarioRepository.save(ProprietarioDados.getProprietarioModelAtualizado());
    }

    @AfterEach
    void tearDown() {
        proprietarioRepository.deleteAll();
    }

    @Test
    void testeListarTodos() {

        int pagina = 0;
        int tamanho = 10;

        var resultado = listarTodosProprietarioController.listarTodos(pagina, tamanho);

        assertEquals(200, resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste Conteúdo",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getBody().getContent().get(0).getId()),
                () -> assertEquals("77b11aa035ed1f735459a1p0", resultado.getBody().getContent().get(1).getId()),
                () -> assertEquals("João", resultado.getBody().getContent().get(0).getNome()),
                () -> assertEquals("Maria", resultado.getBody().getContent().get(1).getNome())
        );

    }

    @Test
    void testeListarTodosPaginaInvalida() {

        int pagina = 1;
        int tamanho = 10;

        var resultado = listarTodosProprietarioController.listarTodos(pagina, tamanho);

        assertEquals(200, resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages()),
                () -> assertEquals(0, resultado.getBody().getContent().size())
        );

        assertTrue(resultado.getBody().getContent().isEmpty());

    }

}