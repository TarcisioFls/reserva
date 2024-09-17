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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Testcontainers
public class DeletaProprietarioPorIdControllerTesteIntegracao {

    @Autowired
    private DeletaProprietarioPorIdController deletaProprietarioPorIdController;

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
    void testeDeletaProprietarioPorId() {
        var id = "66c67aa035ed1f735450b7a2";

        deletaProprietarioPorIdController.deletePorId(id);

        assertFalse(proprietarioRepository.existsById(id));
    }

    @Test
    void testeDeletaProprietarioPorIdInexistente() {
        var id = "idInexistente";

        try {
            deletaProprietarioPorIdController.deletePorId(id);
            fail();
        } catch (ExceptionAdvice e) {
            assertEquals("Proprietário não encontrado", e.getMessage());
            assertEquals(404, e.getCodigoError().getCodigo());
        }
    }

}
