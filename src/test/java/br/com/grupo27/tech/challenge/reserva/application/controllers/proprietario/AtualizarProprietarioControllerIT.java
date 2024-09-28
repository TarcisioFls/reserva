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

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.AtualizarProprietarioDados.getAtualizarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioModel;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class AtualizarProprietarioControllerIT {

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
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();

        var proprietarioResponseEntity = atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);

        assertEquals(200, proprietarioResponseEntity.getStatusCode().value());
        assertTrue(nonNull(proprietarioResponseEntity.getBody()));
        assertEquals("Maria", proprietarioResponseEntity.getBody().getNome());
    }

    @Test
    void testeAtualizarProprietarioMatendoOEmail() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setEmail("joao@teste.com");

        var proprietarioResponseEntity  = atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);

        assertEquals(200, proprietarioResponseEntity.getStatusCode().value());
        assertTrue(nonNull(proprietarioResponseEntity.getBody()));
        assertEquals("Maria", proprietarioResponseEntity.getBody().getNome());
    }

    @Test
    void testeAtualizarProprietarioComIdInexistente() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();

        var proprietarioResponseEntity = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("77b68aa035ed1f735450b9g9", atualizarProprietarioRequest);
        });

        assertEquals("Proprietário não encontrado", proprietarioResponseEntity.getMessage());
        assertEquals(404, proprietarioResponseEntity.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailJaExistente() {

        var proprietarioModel = getProprietarioModel();
        proprietarioModel.setId("77b68aa035ed1f735450b7a2");
        proprietarioModel.setEmail("maria@teste.com");
        proprietarioRepository.save(proprietarioModel);

        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Email já cadastrado", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());

    }

    @Test
    void testeAtualizarProprietarioComNomeEmBranco() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setNome("");

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Nome é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComNomeNull() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setNome(null);

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Nome é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailEmBranco() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setEmail("");

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Email é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComEmailNull() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setEmail(null);

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Email é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComSenhaEmBranco() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setSenha("");

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Senha é obrigatória", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComSenhaNull() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setSenha(null);

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Senha é obrigatória", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComTelefoneEmBranco() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setTelefone("");

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Telefone é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComTelefoneNull() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setTelefone(null);

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("Telefone é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComCpfEmBranco() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setCpf("");

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("CPF é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

    @Test
    void testeAtualizarProprietarioComCpfNull() {
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        atualizarProprietarioRequest.setCpf(null);

        var resultado = assertThrows(ExceptionAdvice.class, () -> {
            atualizarProprietarioController.atualizar("66c67aa035ed1f735450b7a2", atualizarProprietarioRequest);
        });

        assertEquals("CPF é obrigatório", resultado.getMessage());
        assertEquals(400, resultado.getCodigoError().getCodigo());
    }

}
