package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados;
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

import java.util.Objects;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class ListarTodosRestauranteControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarTodosRestauranteController listarTodosRestauranteController;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        restauranteRepository.save(getRestauranteModel());
        var restauranteModel = AtualizarRestauranteDados.getRestauranteModel();
        restauranteModel.setId("66c67aa035ed1f735450b7a3");
        restauranteRepository.save(restauranteModel);
    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testeListarTodos() {

        int pagina = 0;
        int tamanho = 10;

        var response = listarTodosRestauranteController.listarTodos(pagina, tamanho);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(2, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste conteúdo",
                () -> assertEquals(2, Objects.requireNonNull(response.getBody().getContent()).size()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", response.getBody().getContent().get(0).getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a3", response.getBody().getContent().get(1).getId()),
                () -> assertEquals("Magina", response.getBody().getContent().get(0).getNome()),
                () -> assertEquals("Rikimaru", response.getBody().getContent().get(1).getNome())
        );
    }

    @Test
    void testeListarTodosPaginaInvalida() {

        int pagina = 1;
        int tamanho = 10;

        var response = listarTodosRestauranteController.listarTodos(pagina, tamanho);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(2, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages())
        );

        assertTrue(response.getBody().getContent().isEmpty());
    }

}
