package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.BuscarRestaurantePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("restaurante")
public class BuscarRestaurantePorIdController {

    private final BuscarRestaurantePorIdUserCaseFactory buscarRestaurantePorIdUserCaseFactory;

    private final BuscarRestaurantePorIdPresenter buscarRestaurantePorIdPresenter;

    private final RestauranteRepository restauranteRepository;

    private final RestaurantePresenter restaurantePresenter;

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable String id) {

        var buscarRestaurantePorIdUserCase = buscarRestaurantePorIdUserCaseFactory.buildBuscarRestaurantePorIdUserCase(buscarRestaurantePorIdPresenter, restaurantePresenter, restauranteRepository);
        var buscarRestaurantePorIdOutput = buscarRestaurantePorIdUserCase.buscarPorId(id);
        var restauranteResponse = buscarRestaurantePorIdPresenter.buscarRestaurantePorIdOutputEmRestauranteResponse(buscarRestaurantePorIdOutput);

        return ResponseEntity.ok(restauranteResponse);
    }
}
