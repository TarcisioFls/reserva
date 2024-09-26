package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.CriarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.CriarReservaPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getClienteDepoisDeSalvar;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaSemId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservasDiferentes;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarReservaUserCaseTest extends TesteConfig {

    @InjectMocks
    private CriarReservaUserCase criarReservaUserCase;

    @Mock
    private CriarReservaPresenter criarReservaPresenter;

    @Mock
    private CriarReservaGateway criarReservaGateway;

    @Mock
    private BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    @Mock
    private BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;

    @Mock
    private BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;

    @Mock
    private BuscarClientePorIdGateway buscarClientePorIdGateway;

    @Mock
    private Clock clock;

    @BeforeEach
    void init() {
        var dataHora = LocalDateTime.of(2024, 1, 1, 20, 0);
        var fixed = Clock.fixed(dataHora.toInstant(Clock.systemDefaultZone().getZone().getRules().getOffset(dataHora)), Clock.systemDefaultZone().getZone());
        doReturn(fixed.instant()).when(clock).instant();
        doReturn(fixed.getZone()).when(clock).getZone();
    }

    @Test
    void testeCriar() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        var reservaComId = getReservaComId();

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.of(getRestaurante()));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora()))
                .thenReturn(Optional.of(getReservasDiferentes()));
        when(buscarReservasPorClientIdGateway.buscarPorClientIdEDataHora(reservaSemId)).thenReturn(List.of());
        when(criarReservaGateway.criar(reservaSemId)).thenReturn(reservaComId);
        when(criarReservaPresenter.reservaEmCriarReservaOutput(reservaComId)).thenReturn(getCriarReservaOutput());

        var result = criarReservaUserCase.criar(criarReservaInput);

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(1)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(1)).criar(reservaSemId);
        verify(criarReservaPresenter, times(1)).reservaEmCriarReservaOutput(reservaComId);

        assertNotNull(result);
    }

    @Test
    void testeCriarComDataHoraPassado() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        reservaSemId.setDataHora(LocalDateTime.of(2000, 1, 1, 20, 0));

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Não é possível reservar para o passado", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(0)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(0)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(0)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComClienteNaoEncontrado() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.empty());

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Cliente não encontrado", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(0)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(0)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComRestauranteNaoEncontrado() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.empty());

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Restaurante não encontrado", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(0)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComCapacidadeDoRestauranteAtingida() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        var restaurante = getRestaurante();
        restaurante.setCapacidade(4);
        var reservas = getReservasDiferentes();

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.of(restaurante));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora()))
                .thenReturn(Optional.of(reservas));

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Capacidade máxima do restaurante atingida", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComClienteJaPossuiReservaNesteRestaurante() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        var reservas = List.of(getReservaComId());

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.of(getRestaurante()));
        when(buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora()))
                .thenReturn(Optional.of(reservas));

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Cliente já possui reserva neste restaurante", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComReservaForaDoHorarioPermitido() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        var restaurante = getRestaurante();
        restaurante.setHoraAbertura("10:00");
        restaurante.setHoraFechamento("18:00");

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.of(restaurante));

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Reserva fora do horário permitido", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(0)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

    @Test
    void testeCriarComClienteJaPossuiReservaNesteHorario() {

        var criarReservaInput = getCriarReservaInput();
        var reservaSemId = getReservaSemId();
        var reservas = List.of(getReservaComId());

        when(criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput)).thenReturn(reservaSemId);
        when(buscarClientePorIdGateway.buscarClientePorId(reservaSemId.getClienteId())).thenReturn(Optional.of(getClienteDepoisDeSalvar()));
        when(buscarRestaurantePorIdGateway.buscarPorId(reservaSemId.getRestauranteId())).thenReturn(Optional.of(getRestaurante()));
        when(buscarReservasPorClientIdGateway.buscarPorClientIdEDataHora(reservaSemId)).thenReturn(reservas);

        var exception = assertThrows(ExceptionAdvice.class, () -> criarReservaUserCase.criar(criarReservaInput));

        assertEquals("Cliente já possui reserva neste horário", exception.getMessage());

        verify(criarReservaPresenter, times(1)).criarReservaInputEmReserva(criarReservaInput);
        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(reservaSemId.getClienteId());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(reservaSemId.getRestauranteId());
        verify(buscarReservasPorRestauranteIdGateway, times(1)).buscarPorRestauranteIdEDataHora(reservaSemId.getRestauranteId(), reservaSemId.getDataHora());
        verify(buscarReservasPorClientIdGateway, times(1)).buscarPorClientIdEDataHora(reservaSemId);
        verify(criarReservaGateway, times(0)).criar(reservaSemId);
        verify(criarReservaPresenter, times(0)).reservaEmCriarReservaOutput(reservaSemId);
    }

}