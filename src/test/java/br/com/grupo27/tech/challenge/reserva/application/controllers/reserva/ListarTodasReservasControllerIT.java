package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
class ListarTodasReservasControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarTodasReservasController listarTodasReservasController;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        reservaRepository.save(CriarReservaDados.getReservaModelComId());
        var reservaModel = CriarReservaDados.getReservaModelComId();
        reservaModel.setId(null);
        reservaModel.setClienteId("clientId");
        reservaRepository.save(reservaModel);

    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    void testeListarTodos() {

        var reservas = reservaRepository.findAll();
        var segundaReservaId = reservas.get(1).getId();

        var resultado = listarTodasReservasController.listarTodos(0, 10);


        assertAll(
            () -> assertNotNull(resultado),
            () -> assertNotNull(resultado.getBody()),
            () -> assertNotNull(resultado.getBody().getContent()),
            () -> assertEquals(200, resultado.getStatusCode().value()),
            () -> assertEquals("66eb8772fd532626f457c740", resultado.getBody().getContent().getFirst().getId()),
            () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getBody().getContent().getFirst().getRestauranteId()),
            () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getBody().getContent().getFirst().getClienteId()),
            () -> assertEquals(segundaReservaId, resultado.getBody().getContent().get(1).getId()),
            () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getBody().getContent().get(1).getRestauranteId()),
            () -> assertEquals("clientId", resultado.getBody().getContent().get(1).getClienteId())
        );


    }

}