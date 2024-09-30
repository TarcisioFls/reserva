package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.BuscarReservaPorIdController;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
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

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.BuscarReservaPorIdDados.ID;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.BuscarReservaPorIdDados.getReservaModel;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class BuscarReservaPorIdControllerTesteIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BuscarReservaPorIdController buscarReservaPorIdController;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        reservaRepository.save(getReservaModel());
    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    void testBuscarReservaPorId() {

        var response = buscarReservaPorIdController.buscarPorId(ID);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
        assertEquals(ID, response.getBody().getId());
    }

    @Test
    void testBuscarReservaPorIdNaoEncontrado() {

        var idInexistente = "6548a65df86548a65s84ddf5";

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> buscarReservaPorIdController.buscarPorId(idInexistente));

        assertEquals("Reserva não encontrada", exceptionAdvice.getMessage());
        assertEquals(404, exceptionAdvice.getCodigoError().getCodigo());
    }
}