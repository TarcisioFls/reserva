package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
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

import java.time.LocalTime;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.*;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class AtualizarRestauranteControllerTesteIntegracao {

    private AtualizarRestauranteRequest request;

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AtualizarRestauranteController atualizarRestauranteController;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        restauranteRepository.save(getRestauranteModel());
//        request = getAtualizarRestauranteRequest();

        request = new AtualizarRestauranteRequest("Teste", "Teste", "Teste", getAtualizarRestauranteRequest().getHoraAbertura(), getAtualizarRestauranteRequest().getHoraFechamento()
        , getAtualizarRestauranteRequest().getCapacidade(), getAtualizarRestauranteRequest().getTipoCozinhaList(), getAtualizarRestauranteRequest().getProprietario());
    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testAtualizarRestaurante() {

        var response = atualizarRestauranteController.atualizar(ID_TESTE, request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("Rikimaru", response.getBody().getNome());
    }

    @Test
    void testAtualizarRestauranteComIdNaoEncontrado() {

        var idInexistente = "99c98bb035ed1f735450b8c5";

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> atualizarRestauranteController.atualizar(idInexistente, request));

        assertEquals("Restaurante não encontrado", exceptionAdvice.getMessage());
        assertEquals(404, exceptionAdvice.getCodigoError().getCodigo());
    }

    void testAtualizarRestauranteComNomeNull() {
        var atualizarRequest = getAtualizarRestauranteRequest();
        atualizarRequest.setNome(null);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> atualizarRestauranteController.atualizar(ID_TESTE, request));

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }
}
