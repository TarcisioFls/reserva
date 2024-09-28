package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getListarReservasPorRestauranteIdOutputPagedModel;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListarReservasPorRestauranteIdPresenterImplTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorRestauranteIdPresenterImpl listarReservasPorRestauranteIdPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeGetListarReservasPorRestauranteInput() {
        var resultado = listarReservasPorRestauranteIdPresenter.getListarReservasPorRestauranteInput(0, 10, "66c67aa035ed1f735450b7a2");

        assertAll(
                () -> assertEquals(0, resultado.getPagina()),
                () -> assertEquals(10, resultado.getTamanho()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getRestauranteId())
        );
    }

    @Test
    void testeListarReservasPorRestauranteIdOutputPageModelEmReservaResponsePageModel() {
        var output = getListarReservasPorRestauranteIdOutputPagedModel();
        var resultado = listarReservasPorRestauranteIdPresenter.listarReservasPorRestauranteIdOutputPageModelEmReservaResponsePageModel(output);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("66c67aa035", resultado.getContent().getFirst().getId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getContent().getFirst().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getFirst().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getFirst().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getFirst().getStatus()),
                () -> assertEquals("77b11aa035", resultado.getContent().getLast().getId()),
                () -> assertEquals("77b57d371994ae7f1b5e1od8", resultado.getContent().getLast().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getLast().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getLast().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getLast().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getLast().getStatus())
        );

    }

    @Test
    void testePageReservaModelEmPageReservaOutput() {
        var reservaPagedModel = ListarReservasPorRestauranteIdDados.getPageReserva();
        var resultado = listarReservasPorRestauranteIdPresenter.pageReservaModelEmPageReservaOutput(reservaPagedModel);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("66c67aa035", resultado.getContent().getFirst().getId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getContent().getFirst().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getFirst().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getFirst().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getFirst().getStatus()),
                () -> assertEquals("77b11aa035", resultado.getContent().getLast().getId()),
                () -> assertEquals("77b57d371994ae7f1b5e1od8", resultado.getContent().getLast().getClienteId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getLast().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getLast().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getLast().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getLast().getStatus())
        );
    }
}