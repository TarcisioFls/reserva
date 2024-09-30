package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.AtualizarReservaController;
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
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getReservaModel;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@Testcontainers
class AtualizarReservaControllerTesteIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AtualizarReservaController atualizarReservaController;

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
        reservaRepository.save(getReservaModel());

        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 1, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
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
    void testeAtualizarPorId() {

        var id  = "0f55297d-8e66-4914-b22a-4c0e4d646794";
        var atualizarReservaRequest = getAtualizarReservaRequest();
        atualizarReservaRequest.setClienteId(clienteRepository.findAll().getFirst().getId());
        atualizarReservaRequest.setRestauranteId(restauranteRepository.findAll().getFirst().getId());

        var resultado = atualizarReservaController.atualizarPorId(id, atualizarReservaRequest);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(200, resultado.getStatusCode().value()),
                () -> assertNotNull(resultado.getBody()),
                () -> assertEquals(id, resultado.getBody().getId()),
                () -> assertEquals(atualizarReservaRequest.getDataHora(), resultado.getBody().getDataHora()),
                () -> assertEquals(atualizarReservaRequest.getQuantidadePessoas(), resultado.getBody().getQuantidadePessoas()),
                () -> assertEquals(atualizarReservaRequest.getRestauranteId(), resultado.getBody().getRestauranteId()),
                () -> assertEquals(atualizarReservaRequest.getClienteId(), resultado.getBody().getClienteId())
        );
    }

}