package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@Testcontainers
public class CriarReservaControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private CriarReservaController criarReservaController;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @MockBean
    private Clock clock;

    @BeforeEach
    void setUp() {

        reservaRepository.deleteAll();
        restauranteRepository.deleteAll();
        clienteRepository.deleteAll();

        restauranteRepository.save(getRestauranteModel());
        clienteRepository.save(getClienteModel());

        Clock fixedClock = Clock.fixed(LocalDateTime.of(2023, 10, 1, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
        restauranteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    void testeCriarReserva() {
        var criarReservaRequest = getCriarReservaRequest();
        var clienteModel = clienteRepository.findAll().getFirst();
        var restauranteModel = restauranteRepository.findAll().getFirst();
        criarReservaRequest.setClienteId(clienteModel.getId());
        criarReservaRequest.setRestauranteId(restauranteModel.getId());
        criarReservaRequest.setDataHora(LocalDateTime.now(clock)); // Use the mocked clock here

        var reservaResponse = criarReservaController.criar(criarReservaRequest);

        assertNotNull(reservaResponse);
        assertEquals(200, reservaResponse.getStatusCode().value());
        assertEquals(1, reservaRepository.count());
        assertEquals("66c67aa035ed1f735450b7a2", reservaResponse.getBody().getRestauranteId());
        assertEquals("66c67aa035ed1f735450b7a2", reservaResponse.getBody().getClienteId());
    }
}
