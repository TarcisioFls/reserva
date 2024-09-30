package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getAtualizarReservaOutputCancelado;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getReservaCancelada;
import static org.junit.jupiter.api.Assertions.*;

class AtualizarStatusReservaPresenterImplTest extends TesteConfig {

    @InjectMocks
    private AtualizarStatusReservaPresenterImpl atualizarStatusReservaPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeAtualizarReservaOutputEmReservaResponse() {

        var resultado = atualizarStatusReservaPresenter.atualizarReservaOutputEmReservaResponse(getAtualizarReservaOutputCancelado());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("0f55297d-8e66-4914-b22a-4c0e4d646794", resultado.getId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getRestauranteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getClienteId()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals("2024-01-01T20:00", resultado.getDataHora().toString()),
                () -> assertEquals(ReservaStatus.CANCELADO, resultado.getStatus())
        );
    }

    @Test
    void testeReservaEmAtualizarReservaOutput() {

        var resultado = atualizarStatusReservaPresenter.reservaEmAtualizarReservaOutput(getReservaCancelada());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("0f55297d-8e66-4914-b22a-4c0e4d646794", resultado.getId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getRestauranteId()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals("2024-01-01T20:00", resultado.getDataHora().toString()),
                () -> assertEquals(ReservaStatus.CANCELADO, resultado.getStatus())
        );
    }

}