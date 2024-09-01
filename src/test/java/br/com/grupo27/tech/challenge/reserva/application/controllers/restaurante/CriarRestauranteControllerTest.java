package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.CriarRestauranteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.CriarRestauranteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteControllerTest {

    @Mock
    private CriarRestaurantePresenter criarRestaurantePresenter;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private CriarRestauranteUserCase criarRestauranteUserCase;

    @InjectMocks
    private CriarRestauranteController criarRestauranteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criarRestauranteUserCase = new CriarRestauranteUserCase(
                new CriarRestauranteAdapter(restauranteRepository, restaurantePresenter),
                criarRestaurantePresenter
        );
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        CriarRestauranteRequest request = getCriarRestauranteRequest();
        CriarRestauranteInput input = getCriarRestauranteInput();
        CriarRestauranteOutput output = getCriarRestauranteOutput();
        RestauranteResponse response = getRestauranteResponse();

        when(criarRestaurantePresenter.criarRestauranteParaCriarRestauranteInput(request)).thenReturn(input);
        when(criarRestauranteUserCase.criar(input)).thenReturn(output);
        when(criarRestaurantePresenter.criarRestauranteOutputParaRestauranteResponse(output)).thenReturn(response);

        var resultado = criarRestauranteController.criar(request);

        assertEquals(response, resultado.getBody());
        verify(criarRestaurantePresenter, times(1)).criarRestauranteParaCriarRestauranteInput(request);
        verify(criarRestauranteUserCase, times(1)).criar(input);
        verify(criarRestaurantePresenter, times(1)).criarRestauranteOutputParaRestauranteResponse(output);
    }
}