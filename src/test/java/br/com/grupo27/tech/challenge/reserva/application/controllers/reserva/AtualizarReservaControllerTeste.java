package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.AtualizarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.AtualizarReservaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.AtualizarReservaUserCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtualizarReservaController.class)
class AtualizarReservaControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtualizarReservaUserCaseFactory atualizarReservaUserCaseFactory;

    @MockBean
    private AtualizarReservaUserCase atualizarReservaUserCase;

    @MockBean
    private AtualizarReservaPresenter atualizarReservaPresenter;

    @MockBean
    private Clock clock;

    @MockBean
    private BuscarClientePorIdGateway buscarClientePorIdGateway;

    @MockBean
    private BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    @MockBean
    private BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;

    @MockBean
    private BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 1, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @Test
    void testeAtualizarPorId() throws Exception {

        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";
        var request = new AtualizarReservaRequest();

        when(atualizarReservaUserCaseFactory.buildAtualizarReservaUserCase(
                atualizarReservaPresenter,
                clock,
                buscarClientePorIdGateway,
                buscarRestaurantePorIdGateway,
                buscarReservasPorRestauranteIdGateway,
                buscarReservasPorClientIdGateway))
                .thenReturn(atualizarReservaUserCase);
        when(atualizarReservaPresenter.atualizarReservaRequestEmAtualizarReservaInput(id, request))
                .thenReturn(getAtualizarReservaInput());
        when(atualizarReservaUserCase.atualizar(any()))
                .thenReturn(getAtualizarReservaOutput());
        when(atualizarReservaPresenter.atualizarReservaOutputEmReservaResponse(getAtualizarReservaOutput()))
                .thenReturn(getAtualizarReservaResponse());

        mockMvc.perform(put("/reservas/" + id)
                .contentType("application/json")
                .content(asJsonString(request)))
                .andExpect(status().isOk());

        verify(atualizarReservaUserCaseFactory, times(1)).buildAtualizarReservaUserCase(
                atualizarReservaPresenter,
                clock,
                buscarClientePorIdGateway,
                buscarRestaurantePorIdGateway,
                buscarReservasPorRestauranteIdGateway,
                buscarReservasPorClientIdGateway);
        verify(atualizarReservaPresenter, times(1)).atualizarReservaRequestEmAtualizarReservaInput(id, request);
        verify(atualizarReservaUserCase, times(1)).atualizar(any());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaOutputEmReservaResponse(getAtualizarReservaOutput());

    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(obj);
    }

}