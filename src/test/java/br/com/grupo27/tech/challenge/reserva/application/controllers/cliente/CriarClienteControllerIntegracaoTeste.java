package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.CriarClienteController;
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

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModelSemId;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComCpfEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComCpfNull;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComEmailBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComEmailNull;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComNomeEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComNomeNull;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComSenhaEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComSenhaNull;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComTelefoneEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteRequestComTelefoneNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Testcontainers(parallel = true)
public class CriarClienteControllerIntegracaoTeste {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private CriarClienteController criarClienteController;

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
    void testCriarCliente() {
        var request = getCriarClienteRequest();

        var response = criarClienteController.criarCliente(request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("João Rodrigo", response.getBody().getNome());
    }

    @Test
    void testeCriarClienteEmailJaExistente(){
        var criarClienteRequest = getCriarClienteRequest();

        clienteRepository.save(getClienteModelSemId());
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(criarClienteRequest)
        );

        assertEquals("Email já cadastrado", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComNomeEmBranco(){
        var request = getCriarClienteRequestComNomeEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("O campo nome deve ser preenchido com o nome e sobrenome", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComNomeNull(){
        var request = getCriarClienteRequestComNomeNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("O campo nome deve ser preenchido com o nome e sobrenome", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComEmailEmBranco(){
        var request = getCriarClienteRequestComEmailBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComEmailNull(){
        var request = getCriarClienteRequestComEmailNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComSenhaEmBranco(){
        var request = getCriarClienteRequestComSenhaEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComSenhaNull(){
        var request = getCriarClienteRequestComSenhaNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComTelefoneEmBranco(){
        var request = getCriarClienteRequestComTelefoneEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteRequestComTelefoneNull(){
        var request = getCriarClienteRequestComTelefoneNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteComCpfEmBranco(){
        var request = getCriarClienteRequestComCpfEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarClienteRequestComCpfNull(){
        var request = getCriarClienteRequestComCpfNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarClienteController.criarCliente(request)
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }


}