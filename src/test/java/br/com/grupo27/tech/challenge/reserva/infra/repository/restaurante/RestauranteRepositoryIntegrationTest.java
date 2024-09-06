package br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante;

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

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModelSemId;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class RestauranteRepositoryIntegrationTest {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testSalvarRestaurante() {
        var restaurante = getRestauranteModelSemId();

        restauranteRepository.save(restaurante);
        var restaurantes = restauranteRepository.findAll();

        assertEquals(1, restaurantes.size());
        assertEquals(restaurante, restaurantes.getFirst());
    }
}