package br.com.grupo27.tech.challenge.reserva.infra.repository.cliente;

import br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class ClienteRepositoryIntegrationTeste {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        clienteRepository.deleteAll();
    }

    @Test
    void testeSalvarCliente(){
        var cliente = ClienteDados.getClienteModelSemId();

        clienteRepository.save(cliente);
        var clientes = clienteRepository.findAll();

        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));
        }

}
