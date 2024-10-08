package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.CriarRestauranteController;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteRequest;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class CriarRestauranteControllerIT {

    private CriarRestauranteRequest request;

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private CriarRestauranteController criarRestauranteController;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        request = getCriarRestauranteRequest();
        var proprietario = ProprietarioDados.getProprietarioModel();
        proprietario.setId("44c67aa035ed1f735450b72a");
        proprietarioRepository.save(proprietario);

    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testCriarRestaurante() {

        var response = criarRestauranteController.criar(request);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("Magina", response.getBody().getNome());
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testCriarRestauranteComNomeInvalido(String nomeInvalido) {
        request.setNome(nomeInvalido);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> criarRestauranteController.criar(request));

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
        assertEquals(400, exceptionAdvice.getCodigoError().getCodigo());
    }

    static Stream<String> nomesInvalidos() {
        return Stream.of("", " ", null);
    }
}
