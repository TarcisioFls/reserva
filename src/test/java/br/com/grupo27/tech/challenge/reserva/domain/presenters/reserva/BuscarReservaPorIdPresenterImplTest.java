package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.BuscarReservaPorIdOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.BuscarReservaPorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BuscarReservaPorIdPresenterImplTest extends TesteConfig {

    private BuscarReservaPorIdOutput buscarReservaPorIdOutput;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private BuscarReservaPorIdPresenterImpl buscarReservaPorIdPresenter;

    @BeforeEach
    void setUpMocks() {
        buscarReservaPorIdOutput = getBuscarReservaPorIdOutput();
    }

    @Test
    void testeReservaEmBuscarReservaPorIdOutput() {

        var reserva = getReserva();
        var expectativaOutput = buscarReservaPorIdOutput;

        var resultado = buscarReservaPorIdPresenter.reservaEmBuscarReservaPorIdOutput(reserva);

        assertEquals(expectativaOutput, resultado);
        verify(mapper, times(1)).map(reserva, BuscarReservaPorIdOutput.class);
    }

    @Test
    void testeBuscarReservaPorIdOutputEmReservaResponse() {

        var expectativaResponse = getReservaResponse();

        var resultado = buscarReservaPorIdPresenter.buscarReservaPorIdOutputEmReservaResponse(buscarReservaPorIdOutput);

        assertEquals(expectativaResponse, resultado);
        verify(mapper, times(1)).map(buscarReservaPorIdOutput, ReservaResponse.class);
    }
}