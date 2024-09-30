package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.DeletaReservaPorIdController;
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

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados.getReservaModel;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Testcontainers
public class DeletaReservaPorIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private DeletaReservaPorIdController deletaReservaPorIdController;

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
    void testaDeletaReservaPorId(){

        var id = "66eb8772fd532626f457c740";

        deletaReservaPorIdController.deletaPorId(id);

        assertFalse(reservaRepository.existsById(id));
    }


}
