package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getListarReservasPorClienteInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getReservaModelPage;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getReservaPageList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarReservasPorClienteIdAdapterTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorClienteIdAdapter listarReservasPorClienteIdAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeListarPorClienteId() {

        var input = getListarReservasPorClienteInput();
        var pageRequest = PageRequest.of(input.getPagina(), input.getTamanho());

        when(reservaRepository.findByClienteId(input.getClienteId(), pageRequest))
                .thenReturn(getReservaModelPage());
        when(reservaPresenter.pageReservaModelListEmPageReservaList(getReservaModelPage()))
                .thenReturn(getReservaPageList());

        PagedModel<Reserva> resultado = listarReservasPorClienteIdAdapter.listarPorClienteId(input);

        assertAll(
                () -> Assertions.assertNotNull(resultado),
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getClienteId()),
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().get(1).getClienteId())
        );

        verify(reservaRepository, times(1)).findByClienteId(input.getClienteId(), pageRequest);
        verify(reservaPresenter, times(1)).pageReservaModelListEmPageReservaList(getReservaModelPage());

    }
}