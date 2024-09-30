package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.DeletaClientePorIdController;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
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

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.DeletaClientePorIdDados.ID_DELETA_TESTE;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.DeletaClientePorIdDados.getClienteModel;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class DeletaClientePorIdControllerIT {

    @Autowired
    private DeletaClientePorIdController deletaClientePorIdController;

    @Autowired
    private ClienteRepository clienteRepository;

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        clienteRepository.save(getClienteModel());
    }

    @AfterEach
    void tearDoown() {
        clienteRepository.deleteAll();
    }

    @Test
    void testeDeletaClientePorId() {
        var id = ID_DELETA_TESTE;

        deletaClientePorIdController.deletaPorId(id);

        assertFalse(clienteRepository.existsById(id));
    }

    @Test
    void testeDeletaClientePorIdInexistente() {
        var id = "idInexistente";

        try {
            deletaClientePorIdController.deletaPorId(id);
            fail();
        } catch (ExceptionAdvice e) {
            assertEquals("Cliente não encontrado", e.getMessage());
            assertEquals(404, e.getCodigoError().getCodigo());
        }
    }
}
