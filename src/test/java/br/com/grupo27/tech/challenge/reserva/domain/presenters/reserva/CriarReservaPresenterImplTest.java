package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaSemId;
import static org.junit.jupiter.api.Assertions.*;

class CriarReservaPresenterImplTest extends TesteConfig {

    @InjectMocks
    private CriarReservaPresenterImpl criarReservaPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeCriarReservaRequestEmCriarReservaInput() {
        var criarReservaRequest = getCriarReservaRequest();
        var reservaEsperada = getCriarReservaInput();

        var resultado = criarReservaPresenter.criarReservaRequestEmCriarReservaInput(criarReservaRequest);

        assertEquals(reservaEsperada, resultado);
    }

    @Test
    void testeCriarReservaOutputEmReservaResponse() {
        var criarReservaOutput = CriarReservaDados.getCriarReservaOutput();
        var reservaEsperada = getReservaComId();

        var resultado = criarReservaPresenter.criarReservaOutputEmReservaResponse(criarReservaOutput);

        assertEquals(reservaEsperada.getId(), resultado.getId());
    }

    @Test
    void testeReservaEmCriarReservaOutput() {
        var reserva = getReservaComId();
        var reservaEsperada = CriarReservaDados.getCriarReservaOutput();

        var resultado = criarReservaPresenter.reservaEmCriarReservaOutput(reserva);

        assertEquals(reservaEsperada.getId(), resultado.getId());
    }

    @Test
    void testeCriarReservaInputEmReserva() {
        var criarReservaInput = getCriarReservaInput();
        var reservaEsperada = getReservaSemId();

        var resultado = criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput);

        assertEquals(reservaEsperada.getRestauranteId(), resultado.getRestauranteId());
    }


}