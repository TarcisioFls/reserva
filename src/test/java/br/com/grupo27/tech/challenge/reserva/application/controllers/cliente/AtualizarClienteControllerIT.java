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


import static br.com.grupo27.tech.challenge.reserva.mock.cliente.AtualizarClienteDados.getAtualizarClienteRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class AtualizarClienteControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AtualizarClienteController atualizarClienteController;

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
    void testeAtualizarCliente() {
        var request = getAtualizarClienteRequest();

        var response = atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("João atualizado", response.getBody().getNome());
    }

    @Test
    void testeAtualizarClienteMatendoOEmail() {
        var request = getAtualizarClienteRequest();
        request.setEmail("joaoatualizado@teste.com");

        var response  = atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("João atualizado", response.getBody().getNome());
    }

    @Test
    void testeAtualizarClienteComIdInexistente() {
        var request = getAtualizarClienteRequest();

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a1", request);
        });

        assertEquals("Cliente não encontrado", exceptionAdvice.getMessage());
        assertEquals(404, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComEmailJaExistente() {

        var clienteModel = getClienteModel();
        clienteModel.setId("55c67aa035ed1f735450b7a2");
        clienteModel.setEmail("joaoatualizado@teste.com");
        clienteRepository.save(clienteModel);
        var request = getAtualizarClienteRequest();

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email já cadastrado", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());

    }

    @Test
    void testeAtualizarClienteComNomeEmBranco() {
        var request = getAtualizarClienteRequest();
        request.setNome("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComNomeNull() {
        var request = getAtualizarClienteRequest();
        request.setNome(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComEmailEmBranco() {
        var request = getAtualizarClienteRequest();
        request.setEmail("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComEmailNull() {
        var request = getAtualizarClienteRequest();
        request.setEmail(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComSenhaEmBranco() {
        var request = getAtualizarClienteRequest();
        request.setSenha("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComSenhaNull() {
        var request = getAtualizarClienteRequest();
        request.setSenha(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComTelefoneEmBranco() {
        var request = getAtualizarClienteRequest();
        request.setTelefone("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComTelefoneNull() {
        var request = getAtualizarClienteRequest();
        request.setTelefone(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComCpfEmBranco() {
        var request = getAtualizarClienteRequest();
        request.setCpf("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarClienteComCpfNull() {
        var request = getAtualizarClienteRequest();
        request.setCpf(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarClienteController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }


}
