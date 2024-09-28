package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaRequest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtualizarReservaPresenterImplTest extends TesteConfig {

    @InjectMocks
    private AtualizarReservaPresenterImpl atualizarReservaPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeAtualizarReservaRequestEmAtualizarReservaInput() {

        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";
        var request =  getAtualizarReservaRequest();

        var resultado = atualizarReservaPresenter.atualizarReservaRequestEmAtualizarReservaInput(id, request);

        assertAll("Testando resultado",
                () -> assertNotNull(resultado),
                () -> assertEquals(id, resultado.getId()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getDataHora()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getRestauranteId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getClienteId())
        );
    }

    @Test
    void testeAtualizarReservaOutputEmReservaResponse() {

        var atualizarReservaOutput = getAtualizarReservaOutput();

        var resultado = atualizarReservaPresenter.atualizarReservaOutputEmReservaResponse(atualizarReservaOutput);

        assertAll("Testando resultado",
                () -> assertNotNull(resultado),
                () -> assertEquals("0f55297d-8e66-4914-b22a-4c0e4d646794", resultado.getId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getRestauranteId()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getStatus())
        );

    }

    @Test
    void testeReservaEmAtualizarReservaOutput() {

        var reserva = AtualizarReservaDados.getReserva();

        var resultado = atualizarReservaPresenter.reservaEmAtualizarReservaOutput(reserva);

        assertAll("Testando resultado",
                () -> assertNotNull(resultado),
                () -> assertEquals("0f55297d-8e66-4914-b22a-4c0e4d646794", resultado.getId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getRestauranteId()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getStatus())
        );
    }

    @Test
    void testeAtualizarReservaInputEmReserva() {

        var reserva = AtualizarReservaDados.getReserva();
        var atualizarReservaInput = atualizarReservaPresenter.atualizarReservaRequestEmAtualizarReservaInput(reserva.getId(), getAtualizarReservaRequest());

        var resultado = atualizarReservaPresenter.atualizarReservaInputEmReserva(reserva, atualizarReservaInput);

        assertAll("Testando resultado",
                () -> assertNotNull(resultado),
                () -> assertEquals("0f55297d-8e66-4914-b22a-4c0e4d646794", resultado.getId()),
                () -> assertEquals("66e8e4563ea03c1d35ba65bb", resultado.getClienteId()),
                () -> assertEquals("66e39d371994ae7f1b5e9ff0", resultado.getRestauranteId()),
                () -> assertEquals(2, resultado.getQuantidadePessoas()),
                () -> assertEquals(LocalDateTime.of(2024, 1, 1, 20, 0), resultado.getDataHora()),
                () -> assertEquals(RESERVADO, resultado.getStatus())
        );
    }
  
}