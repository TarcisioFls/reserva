package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.BuscarRestaurantePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.BuscarRestaurantePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorIdControllerTest {

    @Mock
    private BuscarRestaurantePorIdUserCaseFactory buscarRestaurantePorIdUserCaseFactory;

    @Mock
    private BuscarRestaurantePorIdPresenter buscarRestaurantePorIdPresenter;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @Mock
    private BuscarRestaurantePorIdUserCase buscarRestaurantePorIdUserCase;

    @InjectMocks
    private BuscarRestaurantePorIdController buscarRestaurantePorIdController;

    void testBuscarRestaurantePorId() {
        var id = ID_BUSCAR_TESTE;
        var buscarRestaurantePorIdOutput = getBuscarRestaurantePorIdOutput();
        var restauranteResponse = getRestauranteResponse();

        when(buscarRestaurantePorIdUserCaseFactory.buildBuscarRestaurantePorIdUserCase(buscarRestaurantePorIdPresenter, restaurantePresenter, restauranteRepository))
                .thenReturn(buscarRestaurantePorIdUserCase);
        when(buscarRestaurantePorIdUserCase.buscarPorId(id)).thenReturn(buscarRestaurantePorIdOutput);
        when(buscarRestaurantePorIdPresenter.buscarRestaurantePorIdOutputEmRestauranteResponse(buscarRestaurantePorIdOutput))
                .thenReturn(restauranteResponse);

        var response = buscarRestaurantePorIdController.buscarPorId(id);

        assertEquals(ResponseEntity.ok(restauranteResponse), response);
        verify(buscarRestaurantePorIdUserCaseFactory, times(1))
                .buildBuscarRestaurantePorIdUserCase(buscarRestaurantePorIdPresenter, restaurantePresenter, restauranteRepository);
        verify(buscarRestaurantePorIdUserCase, times(1)).buscarPorId(id);
        verify(buscarRestaurantePorIdPresenter, times(1))
                .buscarRestaurantePorIdOutputEmRestauranteResponse(buscarRestaurantePorIdOutput);
    }
}