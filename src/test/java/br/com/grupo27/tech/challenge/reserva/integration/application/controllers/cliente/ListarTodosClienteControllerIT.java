package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.ListarTodosClienteController;
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
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModelAtualizado;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class ListarTodosClienteControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarTodosClienteController listarTodosClienteController;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        clienteRepository.save(getClienteModel());
        clienteRepository.save(getClienteModelAtualizado());
    }

    @AfterEach
    void tearDown() {
        clienteRepository.deleteAll();
    }

    @Test
    void testeListarTodos(){

        int pagina = 0;
        int tamanho = 10;

        var response = listarTodosClienteController.listarTodos(pagina,tamanho);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(0, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste Conteúdo",
                () -> assertEquals("66c67aa035ed1f735450b7a2", response.getBody().getContent().get(0).getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a1", response.getBody().getContent().get(1).getId()),
                () -> assertEquals("João Rodrigo", response.getBody().getContent().get(0).getNome()),
                () -> assertEquals("João atualizado", response.getBody().getContent().get(1).getNome())
        );


    }

    @Test
    void testeListarTodosPaginaInvalida(){

        int pagina = 1;
        int tamanho = 10;

        var response = listarTodosClienteController.listarTodos(pagina,tamanho);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages()),
                () -> assertEquals(0, response.getBody().getContent().size())
        );

        assertTrue(response.getBody().getContent().isEmpty());


    }

}
