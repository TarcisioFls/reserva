package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados.getPageListarTodasReservasOutput;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListarTodasReservasPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private ListarTodasReservasPresenterImpl listarTodasReservasPresenterImpl;

    @Spy
    private ModelMapper mapper;

    @Test
    void testePageTodasReservasOutputEmPageReservasResponse() {

        var pageListarTodasReservasOutput = getPageListarTodasReservasOutput();

        var resultado = listarTodasReservasPresenterImpl.pageTodasReservasOutputEmPageReservasResponse(pageListarTodasReservasOutput);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertNotNull(resultado.getContent()),
                () -> assertNotNull(resultado.getMetadata()),
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("66c67aa035", requireNonNull(resultado.getContent().getFirst()).getId()),
                () -> assertEquals("restauranteId", requireNonNull(resultado.getContent().getFirst()).getRestauranteId()),
                () -> assertEquals("primeiroClienteId", requireNonNull(resultado.getContent().getFirst()).getClienteId()),
                () -> assertEquals("77b11aa035", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("66e39d", requireNonNull(resultado.getContent().get(1)).getRestauranteId()),
                () -> assertEquals("segundoClienteId", requireNonNull(resultado.getContent().get(1)).getClienteId())
        );

    }

    @Test
    void testePageReservaEmPageTodosProprietariosOutput() {

        var pageReservas = ListarTodasReservasDados.getPageReserva();

        var resultado = listarTodasReservasPresenterImpl.pageReservaEmPageTodosProprietariosOutput(pageReservas);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("66c67aa035", requireNonNull(resultado.getContent().getFirst()).getId()),
                () -> assertEquals("restauranteId", requireNonNull(resultado.getContent().getFirst()).getRestauranteId()),
                () -> assertEquals("primeiroClienteId", requireNonNull(resultado.getContent().getFirst()).getClienteId()),
                () -> assertEquals("77b11aa035", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", requireNonNull(resultado.getContent().get(1)).getRestauranteId()),
                () -> assertEquals("segundoClienteId", requireNonNull(resultado.getContent().get(1)).getClienteId())
        );
    }

}