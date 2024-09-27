package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarReservaAdapterTeste extends TesteConfig {

    @InjectMocks
    private AtualizarReservaAdapter atualizarReservaAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeAtualizarComSucesso() {

        var reserva = getReservaComId();
        var reservaModel = getReservaModelComId();

        when(reservaPresenter.reservaEmReservaModel(reserva)).thenReturn(reservaModel);
        when(reservaRepository.save(reservaModel)).thenReturn(reservaModel);
        when(reservaPresenter.reservaModelEmReserva(reservaModel)).thenReturn(reserva);

        var resultado = atualizarReservaAdapter.atualizar(reserva);

        verify(reservaPresenter, times(1)).reservaEmReservaModel(reserva);
        verify(reservaRepository, times(1)).save(reservaModel);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(reservaModel);

        assertNotNull(resultado);
        assertAll("Testando o resultado da Reserva",
                () -> assertEquals("66eb8772fd532626f457c740", resultado.getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getRestauranteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getClienteId()),
                () -> assertEquals(LocalDateTime.of(2024,1,1,20,0), resultado.getDataHora()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals(RESERVADO, resultado.getStatus())
        );
    }

    @Test
    void testeBuscarPorIdComSucesso() {

        var id = "66eb8772fd532626f457c740";
        var reserva = getReservaComId();
        var reservaModel = getReservaModelComId();

        when(reservaRepository.findById(id)).thenReturn(java.util.Optional.of(reservaModel));
        when(reservaPresenter.reservaModelEmReserva(reservaModel)).thenReturn(reserva);

        var resultado = atualizarReservaAdapter.buscarPorId(id);

        verify(reservaRepository, times(1)).findById(id);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(reservaModel);

        assertTrue(resultado.isPresent());
        assertAll("Testando o resultado da Reserva",
                () -> assertEquals("66eb8772fd532626f457c740", resultado.get().getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.get().getRestauranteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.get().getClienteId()),
                () -> assertEquals(LocalDateTime.of(2024,1,1,20,0), resultado.get().getDataHora()),
                () -> assertEquals(2, resultado.get().getQuantidadePessoas()),
                () -> assertEquals(RESERVADO, resultado.get().getStatus())
        );
    }

    @Test
    void testeBuscarPorIdNaoEncontrado() {

        var id = "66eb8772fd532626f457c740";

        when(reservaRepository.findById(id)).thenReturn(java.util.Optional.empty());

        var resultado = atualizarReservaAdapter.buscarPorId(id);

        verify(reservaRepository, times(1)).findById(id);
        verify(reservaPresenter, never()).reservaModelEmReserva(Mockito.any());

        assertTrue(resultado.isEmpty());
    }

}