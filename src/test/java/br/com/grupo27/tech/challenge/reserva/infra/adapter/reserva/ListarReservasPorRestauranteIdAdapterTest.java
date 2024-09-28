package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getListarReservasPorRestauranteIdInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getPageReserva;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getPageReservaModel;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarReservasPorRestauranteIdAdapterTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorRestauranteIdAdapter listarReservasPorRestauranteIdAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeListarPorRestauranteId() {

        var input = getListarReservasPorRestauranteIdInput();
        var pageRequest = PageRequest.of(input.getPagina(), input.getTamanho());

        when(reservaRepository.findByRestauranteId(input.getRestauranteId(), pageRequest))
                .thenReturn(getPageReservaModel());
        when(reservaPresenter.pageReservaModelListEmPageReservaList(getPageReservaModel()))
                .thenReturn(getPageReserva());

        var resultado = listarReservasPorRestauranteIdAdapter.listarPorRestauranteId(input);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getRestauranteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().get(1).getRestauranteId())
        );


        verify(reservaRepository, times(1))
                .findByRestauranteId(input.getRestauranteId(), pageRequest);
        verify(reservaPresenter, times(1))
                .pageReservaModelListEmPageReservaList(getPageReservaModel());

    }
}