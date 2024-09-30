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
class ListarReservasPorClienteIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarReservasPorClienteIdController listarReservasPorClienteIdController;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();

        var reservaModel1 = getReservaModel();
        var reservaModel2 = getReservaModel();
        reservaModel2.setId(null);
        reservaModel2.setRestauranteId("77b81aa035ed1f735440t2h0");
        var reservaList = List.of(reservaModel1, reservaModel2);
        reservaRepository.saveAll(reservaList);

    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    void testeBuscarPorClienteId() {

        var resultado = listarReservasPorClienteIdController.buscarPorClienteId("66e39d371994ae7f1b5e9ff0", 0, 50);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(200 ,resultado.getStatusCode().value()),
                () -> assertEquals(2, requireNonNull(resultado.getBody()).getContent().size()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", requireNonNull(resultado.getBody()).getContent().getFirst().getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", requireNonNull(resultado.getBody()).getContent().getLast().getClienteId())
        );

    }
}