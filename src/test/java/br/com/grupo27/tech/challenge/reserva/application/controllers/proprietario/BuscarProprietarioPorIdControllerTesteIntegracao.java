package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
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

import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
class BuscarProprietarioPorIdControllerTesteIntegracao {

    @Autowired
    private BuscarProprietarioPorIdController buscarProprietarioPorIdController;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        proprietarioRepository.deleteAll();
        proprietarioRepository.save(getProprietarioModel());
    }

    @AfterEach
    void tearDown() {
        proprietarioRepository.deleteAll();
    }

    @Test
    void testeBuscarProprietarioPorId() {
        var id = "66c67aa035ed1f735450b7a2";

        var resultado = buscarProprietarioPorIdController.buscaPorId(id);

        assertNotNull(resultado.getBody());
        assertEquals(200, resultado.getStatusCode().value());
        assertEquals("João", resultado.getBody().getNome());
    }

    @Test
    void testeBuscarProprietarioPorIdNaoEncontrado() {
        var id = "66c67aa035";

        var resultado = assertThrows(ExceptionAdvice.class, () -> buscarProprietarioPorIdController.buscaPorId(id));

        assertEquals(404, resultado.getCodigoError().getCodigo());
        assertEquals("Proprietário não encontrado", resultado.getMessage());
    }

}