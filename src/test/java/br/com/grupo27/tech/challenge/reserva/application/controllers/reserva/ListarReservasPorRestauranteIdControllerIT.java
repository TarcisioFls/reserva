package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
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

import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados.getReservaModel;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
class ListarReservasPorRestauranteIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarReservasPorRestauranteIdController listarReservasPorRestauranteIdController;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();

        var reservaModel = getReservaModel();
        var reservaModel2 = getReservaModel();
        reservaModel2.setId(null);
        reservaModel2.setClienteId("88b49d371994ae7f1b5e1gs9");
        var reservaList = List.of(reservaModel, reservaModel2);

        reservaRepository.saveAll(reservaList);
    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    void testeBuscarPorRestauranteId() {

        var resultado = listarReservasPorRestauranteIdController.buscarPorRestauranteId("66c67aa035ed1f735450b7a2", 0, 50);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(200, resultado.getStatusCode().value()),
                () -> assertEquals(2, requireNonNull(resultado.getBody()).getContent().size()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getBody()).getContent().getFirst().getRestauranteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getBody()).getContent().getLast().getRestauranteId())
        );

    }
}