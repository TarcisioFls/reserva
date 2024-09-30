package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.BuscarRestaurantePorIdController;
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

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.ID_BUSCAR_TESTE;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.getRestauranteModel;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Testcontainers
public class BuscarRestaurantePorIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BuscarRestaurantePorIdController buscarRestaurantePorIdController;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        restauranteRepository.save(getRestauranteModel());
    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testBuscarRestaurantePorId() {

        var response = buscarRestaurantePorIdController.buscarPorId(ID_BUSCAR_TESTE);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals("Rikimaru", response.getBody().getNome());
    }

    @Test
    void testBuscarRestaurantePorIdNaoEncontrado() {

        var idInexistente = "99c98bb035ed1f735450b8c5";

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> buscarRestaurantePorIdController.buscarPorId(idInexistente));

        assertEquals("Restaurante não encontrado", exceptionAdvice.getMessage());
        assertEquals(404, exceptionAdvice.getCodigoError().getCodigo());
    }
}
