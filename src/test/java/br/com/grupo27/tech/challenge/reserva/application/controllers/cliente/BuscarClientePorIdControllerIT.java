package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

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

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class BuscarClientePorIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BuscarClientePorIdController buscarClientePorIdController;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        clienteRepository.save(getClienteModel());
    }

    @AfterEach
    void tearDown() {
        clienteRepository.deleteAll();
    }

    @Test
    void testeBuscarClientePorId(){

        var id = "66c67aa035ed1f735450b7a2";

        var resultado = buscarClientePorIdController.buscaPorId(id);

        assertNotNull(resultado.getBody());
        assertEquals(200, resultado.getStatusCode().value());
        assertEquals("João Rodrigo", resultado.getBody().getNome());
    }

    @Test
    void testeBuscarClientePorIdNaoEncontrado(){

        var id = "43434343434";

        var resultado = assertThrows(ExceptionAdvice.class,
                () -> buscarClientePorIdController.buscaPorId(id));

        assertEquals(404, resultado.getCodigoError().getCodigo());
        assertEquals("Cliente não encontrado", resultado.getMessage());
    }
}
