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

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.CANCELADO;
import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.CONCLUIDO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
class AtualizarStatusReservaControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AtualizarStatusReservaController atualizarStatusReservaController;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        reservaRepository.save(getReservaModelComId());
    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    void testeAtualizarStatusCancelado() {
        var id = reservaRepository.findAll().getFirst().getId();

        var reservaResponse = atualizarStatusReservaController.atualizarStatus(id, CANCELADO);

        assertAll(
                () -> assertNotNull(reservaResponse),
                () -> assertNotNull(reservaResponse.getBody()),
                () -> assertEquals(id, reservaResponse.getBody().getId()),
                () -> assertEquals(CANCELADO, reservaResponse.getBody().getStatus()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", reservaResponse.getBody().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", reservaResponse.getBody().getRestauranteId())
        );
    }

    @Test
    void testeAtualizarStatusConcluido() {
        var id = reservaRepository.findAll().getFirst().getId();

        var reservaResponse = atualizarStatusReservaController.atualizarStatus(id, CONCLUIDO);

        assertAll(
                () -> assertNotNull(reservaResponse),
                () -> assertNotNull(reservaResponse.getBody()),
                () -> assertEquals(id, reservaResponse.getBody().getId()),
                () -> assertEquals(CONCLUIDO, reservaResponse.getBody().getStatus()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", reservaResponse.getBody().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", reservaResponse.getBody().getRestauranteId())
        );
    }

}