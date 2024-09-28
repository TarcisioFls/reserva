package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.CriarReservaUseCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.CriarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.CriarReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaResponse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CriarReservaController.class)
class CriarReservaControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarReservaUseCaseFactory criarReservaUseCaseFactory;

    @MockBean
    private CriarReservaUserCase criarReservaUserCase;

    @MockBean
    private CriarReservaPresenter criarReservaPresenter;

    @MockBean
    private ReservaPresenter reservaPresenter;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private RestaurantePresenter restaurantePresenter;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ClientePresenter clientePresenter;

    @MockBean
    private Clock clock;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 1, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }


    void testeCriar() throws Exception {

        var request = getCriarReservaRequest();
        var criarReservaInput = getCriarReservaInput();
        var criarReservaOutput = getCriarReservaOutput();
        var reservaResponse = getReservaResponse();

        when(criarReservaUseCaseFactory.buildCriarReservaUseCase(criarReservaPresenter, reservaPresenter,
                reservaRepository, restauranteRepository, restaurantePresenter, clienteRepository, clientePresenter, clock))
                .thenReturn(criarReservaUserCase);

        when(criarReservaPresenter.criarReservaRequestEmCriarReservaInput(request))
                .thenReturn(criarReservaInput);

        when(criarReservaUserCase.criar(criarReservaInput))
                .thenReturn(criarReservaOutput);

        when(criarReservaPresenter.criarReservaOutputEmReservaResponse(criarReservaOutput))
                .thenReturn(reservaResponse);

        mockMvc.perform(post("/reservas")
                        .contentType("application/json")
                        .content(asJsonString(request))
                ).andExpect(status().isOk());


        verify(criarReservaUseCaseFactory, times(1)).buildCriarReservaUseCase(criarReservaPresenter,
                reservaPresenter, reservaRepository, restauranteRepository, restaurantePresenter, clienteRepository,
                clientePresenter, clock);
        verify(criarReservaPresenter, times(1)).criarReservaRequestEmCriarReservaInput(request);
        verify(criarReservaUserCase, times(1)).criar(criarReservaInput);
        verify(criarReservaPresenter, times(1)).criarReservaOutputEmReservaResponse(criarReservaOutput);

    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(obj);
    }

}