package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getAtualizarReservaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getOutraReserva;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getReserva;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarReservaDados.getReservaComHoraNoPassado;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservasDiferentes;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarReservaUserCaseTeste extends TesteConfig {

    @InjectMocks
    private AtualizarReservaUserCase atualizarReservaUserCase;

    @Mock
    private AtualizarReservaPresenter atualizarReservaPresenter;

    @Mock
    private Clock clock;

    @Mock
    private BuscarClientePorIdGateway buscarClientePorIdGateway;

    @Mock
    private BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    @Mock
    private BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;

    @Mock
    private BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;

    @Mock
    private AtualizarReservaGateway atualizarReservaGateway;

    @BeforeEach
    void init() {
        var dataHora = LocalDateTime.of(2024, 1, 1, 20, 0);
        var fixed = Clock.fixed(dataHora.toInstant(Clock.systemDefaultZone().getZone().getRules().getOffset(dataHora)), Clock.systemDefaultZone().getZone());
        doReturn(fixed.instant()).when(clock).instant();
        doReturn(fixed.getZone()).when(clock).getZone();
    }


    @Test
    void testeAtualizar() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));

        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReserva());

        when(buscarClientePorIdGateway.buscarClientePorId(getReserva().getClienteId()))
                .thenReturn(Optional.of(getCliente()));

        when(buscarRestaurantePorIdGateway.buscarPorId(getReserva().getRestauranteId()))
                .thenReturn(Optional.of(getRestaurante()));

        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora()))
                .thenReturn(Optional.of(getReservasDiferentes()));
        when(buscarReservasPorClientIdGateway.buscarPorClientIdEDataHora(reservarComId)).thenReturn(List.of());

        when(atualizarReservaGateway.atualizar(any())).thenReturn(getReserva());

        when(atualizarReservaPresenter.reservaEmAtualizarReservaOutput(getReserva())).thenReturn(getAtualizarReservaOutput());

        var resultado = atualizarReservaUserCase.atualizar(atualizarReservaInput);

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(1)).buscarPorClientIdEDataHora(reservarComId);
        verify(atualizarReservaGateway, times(1)).atualizar(any());
        verify(atualizarReservaPresenter, times(1)).reservaEmAtualizarReservaOutput(getReserva());

        assertNotNull(resultado);

    }

    @Test
    void testeAtualizarComDataNoPassado() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));
        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReservaComHoraNoPassado());

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> atualizarReservaUserCase.atualizar(atualizarReservaInput));

        assertEquals("Não é possível reservar para o passado", exceptionAdvice.getMessage());

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, never()).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, never()).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, never()).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, never()).buscarPorClientIdEDataHora(reservarComId);
        verify(atualizarReservaGateway, never()).atualizar(any());
        verify(atualizarReservaPresenter, never()).reservaEmAtualizarReservaOutput(getReserva());

    }

    @Test
    void testeAtualizarComCapacidadedoRestauranteAtingida() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();
        var restaurante = CriarRestauranteDados.getRestaurante();
        restaurante.setCapacidade(1);

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));
        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReserva());
        when(buscarClientePorIdGateway.buscarClientePorId(getReserva().getClienteId()))
                .thenReturn(Optional.of(getCliente()));
        when(buscarRestaurantePorIdGateway.buscarPorId(getReserva().getRestauranteId()))
                .thenReturn(Optional.of(restaurante));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora()))
                .thenReturn(Optional.of(getReservasDiferentes()));

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> atualizarReservaUserCase.atualizar(atualizarReservaInput));

        assertEquals("Capacidade máxima do restaurante atingida", exceptionAdvice.getMessage());

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, never()).buscarPorClientIdEDataHora(getReserva());
        verify(atualizarReservaGateway, never()).atualizar(any());
        verify(atualizarReservaPresenter, never()).reservaEmAtualizarReservaOutput(getReserva());
    }

    @Test
    void testeAtualizarComClienteJaPossuiReservaNesteRestaurante() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();
        var reservas = List.of(getOutraReserva());

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));
        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReserva());
        when(buscarClientePorIdGateway.buscarClientePorId(getReserva().getClienteId()))
                .thenReturn(Optional.of(getCliente()));
        when(buscarRestaurantePorIdGateway.buscarPorId(getReserva().getRestauranteId()))
                .thenReturn(Optional.of(getRestaurante()));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora()))
                .thenReturn(Optional.of(reservas));

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> atualizarReservaUserCase.atualizar(atualizarReservaInput));

        assertEquals("Cliente já possui reserva neste restaurante", exceptionAdvice.getMessage());

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, never()).buscarPorClientIdEDataHora(reservarComId);
        verify(atualizarReservaGateway, never()).atualizar(any());
        verify(atualizarReservaPresenter, never()).reservaEmAtualizarReservaOutput(getReserva());
    }

    @Test
    void testeAtualizarComReservaForaDoHorarioPermitido() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();
        var restaurante = CriarRestauranteDados.getRestaurante();
        restaurante.setHoraAbertura("21:00");
        restaurante.setHoraFechamento("22:00");

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));
        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReserva());
        when(buscarClientePorIdGateway.buscarClientePorId(getReserva().getClienteId()))
                .thenReturn(Optional.of(getCliente()));
        when(buscarRestaurantePorIdGateway.buscarPorId(getReserva().getRestauranteId()))
                .thenReturn(Optional.of(restaurante));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora()))
                .thenReturn(Optional.of(getReservasDiferentes()));

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> atualizarReservaUserCase.atualizar(atualizarReservaInput));

        assertEquals("Reserva fora do horário permitido", exceptionAdvice.getMessage());

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, never()).buscarPorClientIdEDataHora(reservarComId);
        verify(atualizarReservaGateway, never()).atualizar(any());
        verify(atualizarReservaPresenter, never()).reservaEmAtualizarReservaOutput(getReserva());
    }

    @Test
    void testeAtualizaComClienteJaPossuiReservaNesteHorario() {

        var atualizarReservaInput = getAtualizarReservaInput();
        var reservarComId = getReserva();
        var reservas = List.of(getOutraReserva());

        when(atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId()))
                .thenReturn(Optional.of(reservarComId));
        when(atualizarReservaPresenter.atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput))
                .thenReturn(getReserva());
        when(buscarClientePorIdGateway.buscarClientePorId(getReserva().getClienteId()))
                .thenReturn(Optional.of(getCliente()));
        when(buscarRestaurantePorIdGateway.buscarPorId(getReserva().getRestauranteId()))
                .thenReturn(Optional.of(getRestaurante()));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora()))
                .thenReturn(Optional.of(getReservasDiferentes()));
        when(buscarReservasPorClientIdGateway.buscarPorClientIdEDataHora(reservarComId)).thenReturn(reservas);

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> atualizarReservaUserCase.atualizar(atualizarReservaInput));

        assertEquals("Cliente já possui reserva neste horário", exceptionAdvice.getMessage());

        verify(atualizarReservaGateway, times(1)).buscarPorId(atualizarReservaInput.getId());
        verify(atualizarReservaPresenter, times(1)).atualizarReservaInputEmReserva(reservarComId, atualizarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(getReserva().getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(getReserva().getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservarComId.getRestauranteId(), reservarComId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(1)).buscarPorClientIdEDataHora(reservarComId);
        verify(atualizarReservaGateway, never()).atualizar(any());
        verify(atualizarReservaPresenter, never()).reservaEmAtualizarReservaOutput(getReserva());
    }

}