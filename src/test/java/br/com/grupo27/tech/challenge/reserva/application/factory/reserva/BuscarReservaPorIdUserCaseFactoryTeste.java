package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.BuscarReservaPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuscarReservaPorIdUserCaseFactoryTeste extends TesteConfig {

    @Mock
    private BuscarReservaPorIdPresenter buscarReservaPorIdPresenter;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private BuscarReservaPorIdUserCaseFactory buscarReservaPorIdUserCaseFactory;

    @Test
    void testeBuildBuscarReservaPorIdUserCase() {

        var resultado = buscarReservaPorIdUserCaseFactory.buildBuscarReservaPorIdUserCase(buscarReservaPorIdPresenter, reservaPresenter, reservaRepository);

        assertNotNull(resultado);
    }

    @Test
    void testeBuildBuscarReservaPorIdGateway() {

        var resultado = buscarReservaPorIdUserCaseFactory.buildReservaPorIdGateway(reservaPresenter, reservaRepository);

        assertNotNull(resultado);
    }
}