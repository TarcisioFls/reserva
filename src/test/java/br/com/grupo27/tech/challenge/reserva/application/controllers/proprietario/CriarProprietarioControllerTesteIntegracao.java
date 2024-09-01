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

import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComCpfEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComCpfNull;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComEmailEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComEmailNull;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComNomeEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComNomeNull;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComPasswordEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComPasswordNull;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComTelefoneEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequestComTelefoneNull;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModelSemId;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class CriarProprietarioControllerTesteIntegracao {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private CriarProprietarioController criarProprietarioController;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @BeforeEach
    void setUp() {
        proprietarioRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        proprietarioRepository.deleteAll();
    }

    @Test
    void testeCriarProprietario() {
        var request = getCriarProprietarioRequest();

        var response = criarProprietarioController.criar(request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("João", response.getBody().getNome());
    }

    @Test
    void testeCriarProprietarioEmailJaExistente() {
        var proprietarioModel = getProprietarioModelSemId();
        var criarProprietarioRequest = getCriarProprietarioRequest();

        proprietarioRepository.save(proprietarioModel);
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Email já cadastrado", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComNomeEmBranco() {
        var request = getCriarProprietarioRequestComNomeEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComNomeNull() {
        var request = getCriarProprietarioRequestComNomeNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComEmailEmBranco() {
        var request = getCriarProprietarioRequestComEmailEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComEmailNull() {
        var request = getCriarProprietarioRequestComEmailNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComSenhaEmBranco() {
        var request = getCriarProprietarioRequestComPasswordEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComSenhadNull() {
        var request = getCriarProprietarioRequestComPasswordNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComTelefoneEmBranco() {
        var request = getCriarProprietarioRequestComTelefoneEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComTelefoneNull() {
        var request = getCriarProprietarioRequestComTelefoneNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComCpfEmBranco() {
        var request = getCriarProprietarioRequestComCpfEmBranco();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComCpfNull() {
        var request = getCriarProprietarioRequestComCpfNull();
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(request)
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

}