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

import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModel;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class AtualizarProprietarioControllerTesteIntegracao {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AtualizarProprietarioController atualizarProprietarioController;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

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
    void testeAtualizarProprietario() {
        var request = getAtualizarProprietarioRequest();

        var response = atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("Maria", response.getBody().getNome());
    }

    @Test
    void testeAtualizarProprietarioMatendoOEmail() {
        var request = getAtualizarProprietarioRequest();
        request.setEmail("joao@teste.com");

        var response  = atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("Maria", response.getBody().getNome());
    }

    @Test
    void testeAtualizarProprietarioComIdInexistente() {
        var request = getAtualizarProprietarioRequest();

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("77b68aa035ed1f735450b9g9", request);
        });

        assertEquals("Proprietário não encontrado", exceptionAdvice.getMessage());
        assertEquals(404, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailJaExistente() {

        var proprietarioModel = getProprietarioModel();
        proprietarioModel.setId("77b68aa035ed1f735450b7a2");
        proprietarioModel.setEmail("maria@teste.com");
        proprietarioRepository.save(proprietarioModel);
        var request = getAtualizarProprietarioRequest();

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email já cadastrado", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());

    }

    @Test
    void testeAtualizarProprietarioComNomeEmBranco() {
        var request = getAtualizarProprietarioRequest();
        request.setNome("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComNomeNull() {
        var request = getAtualizarProprietarioRequest();
        request.setNome(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailEmBranco() {
        var request = getAtualizarProprietarioRequest();
        request.setEmail("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailNull() {
        var request = getAtualizarProprietarioRequest();
        request.setEmail(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComSenhaEmBranco() {
        var request = getAtualizarProprietarioRequest();
        request.setSenha("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComSenhaNull() {
        var request = getAtualizarProprietarioRequest();
        request.setSenha(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComTelefoneEmBranco() {
        var request = getAtualizarProprietarioRequest();
        request.setTelefone("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComTelefoneNull() {
        var request = getAtualizarProprietarioRequest();
        request.setTelefone(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComCpfEmBranco() {
        var request = getAtualizarProprietarioRequest();
        request.setCpf("");

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComCpfNull() {
        var request = getAtualizarProprietarioRequest();
        request.setCpf(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", request);
        });

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

}
