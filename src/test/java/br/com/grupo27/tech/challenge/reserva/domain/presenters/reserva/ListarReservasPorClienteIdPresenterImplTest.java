package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getListarReservasPorClienteIdOutputPagedModel;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getReservaPageList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListarReservasPorClienteIdPresenterImplTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorClienteIdPresenterImpl listarReservasPorClienteIdPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeGetListarReservasPorClienteIdInput() {
        var resultado = listarReservasPorClienteIdPresenter.getListarReservasPorClienteIdInput(0, 10, "66c67aa035ed1f735450b7a2");

        assertAll(
                () -> assertEquals(0, resultado.getPagina()),
                () -> assertEquals(10, resultado.getTamanho()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getClienteId())
        );
    }

    @Test
    void testeListarReservasPorClienteIdOutputPageModelEmReservaResponsePageModel() {
        var output = getListarReservasPorClienteIdOutputPagedModel();
        var resultado = listarReservasPorClienteIdPresenter.listarReservasPorClienteIdOutputPageModelEmReservaResponsePageModel(output);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("77ab8882fd752626g657c871", resultado.getContent().getFirst().getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getClienteId()),
                () -> assertEquals("99b3456d371344ab6f1y1e9pad", resultado.getContent().getFirst().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getFirst().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getFirst().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getFirst().getStatus()),
                () -> assertEquals("88bc9993fd862727h758d982", resultado.getContent().getLast().getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getLast().getClienteId()),
                () -> assertEquals("fb008a04-4040-4c70-80ce-693d58f8939f", resultado.getContent().getLast().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getLast().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getLast().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getLast().getStatus())
        );
    }

    @Test
    void testePageReservaModelEmPageReservaOutput() {
        var reservaPagedModel = getReservaPageList();
        var resultado = listarReservasPorClienteIdPresenter.pageReservaModelEmPageReservaOutput(reservaPagedModel);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll(
                () -> assertEquals("77ab8882fd752626g657c871", resultado.getContent().getFirst().getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getFirst().getClienteId()),
                () -> assertEquals("99b3456d371344ab6f1y1e9pad", resultado.getContent().getFirst().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getFirst().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getFirst().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getFirst().getStatus()),
                () -> assertEquals("88bc9993fd862727h758d982", resultado.getContent().getLast().getId()),
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getContent().getLast().getClienteId()),
                () -> assertEquals("fb008a04-4040-4c70-80ce-693d58f8939f", resultado.getContent().getLast().getRestauranteId()),
                () -> assertEquals(2, resultado.getContent().getLast().getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getContent().getLast().getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getContent().getLast().getStatus())
        );
    }
}