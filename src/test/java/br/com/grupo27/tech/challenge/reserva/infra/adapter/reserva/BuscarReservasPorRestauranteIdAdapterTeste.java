package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarReservasPorRestauranteIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private BuscarReservasPorRestauranteIdAdapter buscarReservasPorRestauranteIdAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeBuscarPorRestauranteIdEDataHora() {

        var restauranteId = "66c67aa035ed1f735450b7a2";
        var dataHora = LocalDateTime.of(2024,1,1,20,0);
        var inicioDoDia = dataHora.toLocalDate().atStartOfDay();
        var fimDoDia = dataHora.toLocalDate().atTime(LocalDateTime.MAX.toLocalTime());
        var optionalReservaModelList = Optional.of(List.of(getReservaModelComId()));

        when(reservaRepository.findByRestauranteIdAndDataHoraBetween(restauranteId, inicioDoDia, fimDoDia))
                .thenReturn(optionalReservaModelList);
        when(reservaPresenter.reservaModelEmReserva(any()))
                .thenReturn(CriarReservaDados.getReservaComId());

        var resultado = buscarReservasPorRestauranteIdAdapter
                .buscarPorRestauranteIdEDataHora(restauranteId, dataHora);

        verify(reservaRepository, Mockito.times(1))
                .findByRestauranteIdAndDataHoraBetween(restauranteId, inicioDoDia, fimDoDia);
        verify(reservaPresenter, Mockito.times(1)).reservaModelEmReserva(any());

        assertTrue(resultado.isPresent());
    }

}