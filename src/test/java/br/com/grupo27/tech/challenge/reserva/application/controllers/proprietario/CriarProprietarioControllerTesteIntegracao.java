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
        var criarProprietarioRequest = getCriarProprietarioRequest();

        var proprietarioResponseEntity = criarProprietarioController.criar(criarProprietarioRequest);

        assertEquals(200, proprietarioResponseEntity.getStatusCode().value());
        assertTrue(nonNull(proprietarioResponseEntity.getBody()));
        assertEquals("João", proprietarioResponseEntity.getBody().getNome());
    }

    @Test
    void testeCriarProprietarioEmailJaExistente() {
        var proprietarioModel = getProprietarioModelSemId();
        var criarProprietarioRequest = getCriarProprietarioRequest();

        proprietarioRepository.save(proprietarioModel);
        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Email já cadastrado", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComNomeEmBranco() {
        var criarProprietarioRequest = getCriarProprietarioRequestComNomeEmBranco();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Nome é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComNomeNull() {
        var criarProprietarioRequest = getCriarProprietarioRequestComNomeNull();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Nome é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComEmailEmBranco() {
        var criarProprietarioRequest = getCriarProprietarioRequestComEmailEmBranco();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Email é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComEmailNull() {
        var criarProprietarioRequest = getCriarProprietarioRequestComEmailNull();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Email é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComSenhaEmBranco() {
        var criarProprietarioRequest = getCriarProprietarioRequestComPasswordEmBranco();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Senha é obrigatória", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComSenhadNull() {
        var criarProprietarioRequest = getCriarProprietarioRequestComPasswordNull();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Senha é obrigatória", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComTelefoneEmBranco() {
        var criarProprietarioRequest = getCriarProprietarioRequestComTelefoneEmBranco();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Telefone é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComTelefoneNull() {
        var criarProprietarioRequest = getCriarProprietarioRequestComTelefoneNull();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("Telefone é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComCpfEmBranco() {
        var criarProprietarioRequest = getCriarProprietarioRequestComCpfEmBranco();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("CPF é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeCriarProprietarioComCpfNull() {
        var criarProprietarioRequest = getCriarProprietarioRequestComCpfNull();

        var resultado = assertThrows(
                ExceptionAdvice.class, () -> criarProprietarioController.criar(criarProprietarioRequest)
        );

        assertEquals("CPF é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

}