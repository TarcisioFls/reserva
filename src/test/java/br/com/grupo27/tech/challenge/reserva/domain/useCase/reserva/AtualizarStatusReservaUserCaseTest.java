package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarStatusReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarStatusReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.CANCELADO;
import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getReservaCancelada;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarStatusReservaUserCaseTest extends TesteConfig {

    @InjectMocks
    private AtualizarStatusReservaUserCase atualizarStatusReservaUserCase;

    @Mock
    private BuscarReservaPorIdGateway buscarReservaPorIdGateway;

    @Mock
    private AtualizarStatusReservaGateway atualizarStatusReservaGateway;

    @Mock
    private AtualizarStatusReservaPresenter atualizarStatusReservaPresenter;

    @Test
    void testeAtualizarStatus() {
        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";

        when(buscarReservaPorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getReservaComId()));
        when(atualizarStatusReservaGateway.atualizarStatus(Mockito.any())).thenReturn(getReservaCancelada());
        when(atualizarStatusReservaPresenter.reservaEmAtualizarReservaOutput(Mockito.any())).thenReturn(AtualizarStatusReservaDados.getAtualizarReservaOutputCancelado());

        atualizarStatusReservaUserCase.atualizarStatus(id, CANCELADO);

        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(id);
        verify(atualizarStatusReservaGateway, times(1)).atualizarStatus(Mockito.any());
        verify(atualizarStatusReservaPresenter, times(1)).reservaEmAtualizarReservaOutput(Mockito.any());


    }

    @Test
    void testeAtualizarStatusDiferenteDeCanceladoEConcluido() {
        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";

        var resultado = assertThrows(ExceptionAdvice.class, () -> atualizarStatusReservaUserCase.atualizarStatus(id, RESERVADO));

        assertEquals("Status da reserva inválido", resultado.getMessage());

        verify(buscarReservaPorIdGateway, never()).buscarPorId(id);
        verify(atualizarStatusReservaGateway, never()).atualizarStatus(Mockito.any());
        verify(atualizarStatusReservaPresenter, never()).reservaEmAtualizarReservaOutput(Mockito.any());
    }

    @Test
    void testeAtualizarStatusReservaNaoEncontrada() {
        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";

        when(buscarReservaPorIdGateway.buscarPorId(id)).thenReturn(Optional.empty());

        var resultado = assertThrows(ExceptionAdvice.class, () -> atualizarStatusReservaUserCase.atualizarStatus(id, CANCELADO));

        assertEquals("Reserva não encontrada", resultado.getMessage());

        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(id);
        verify(atualizarStatusReservaGateway, never()).atualizarStatus(Mockito.any());
        verify(atualizarStatusReservaPresenter, never()).reservaEmAtualizarReservaOutput(Mockito.any());
    }

}