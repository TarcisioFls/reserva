package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void testePageReservaModelListEmPageReservaList() {

        var pageReservaModel = ListarTodasReservasDados.getPageReservaModel();

        var resultado = reservaPresenter.pageReservaModelListEmPageReservaList(pageReservaModel);

        assertAll(
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size())
        );

        assertAll(
                () -> assertEquals("66c67aa035", requireNonNull(resultado.getContent().getFirst()).getId()),
                () -> assertEquals("primeiroClienteId", requireNonNull(resultado.getContent().getFirst()).getClienteId()),
                () -> assertEquals("restauranteId", requireNonNull(resultado.getContent().getFirst()).getRestauranteId()),
                () -> assertEquals("77b11aa035", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("segundoClienteId", requireNonNull(resultado.getContent().get(1)).getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", requireNonNull(resultado.getContent().get(1)).getRestauranteId())
        );

    }

}