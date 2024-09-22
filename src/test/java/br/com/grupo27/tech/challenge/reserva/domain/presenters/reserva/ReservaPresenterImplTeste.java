package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private ReservaPresenterImpl reservaPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void reservaModelEmReserva() {
        var reservaModel = getReservaModelComId();
        var reservaEsperada = getReservaComId();

        var resultado = reservaPresenter.reservaModelEmReserva(reservaModel);

        assertEquals(reservaEsperada, resultado);
    }

    @Test
    void reservaEmReservaModel() {
        var reserva = getReservaComId();
        var reservaModelEsperada = getReservaModelComId();

        var resultado = reservaPresenter.reservaEmReservaModel(reserva);

        assertEquals(reservaModelEsperada, resultado);
    }

}