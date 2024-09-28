package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.BuscarRestaurantesUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class BuscarRestaurantesController {

    private final BuscarRestaurantesUserCaseFactory buscarRestaurantesUserCaseFactory;

    private final RestauranteRepository restauranteRepository;

    private final RestaurantePresenter restaurantePresenter;

    private final BuscarRestaurantesPresenter buscarRestaurantesPresenter;

    @GetMapping("/buscar/{buscar}")
    public ResponseEntity<PagedModel<RestauranteResponse>> buscarRestaurantes(@PathVariable String buscar) {

        var buscarRestauranteUserCase = buscarRestaurantesUserCaseFactory
                .buildBuscarRestauranteUserCase(restauranteRepository, restaurantePresenter, buscarRestaurantesPresenter);
        var buscarRestaurantesOutputList = buscarRestauranteUserCase.buscarRestaurantes(buscar);
        var restauranteResponseList = buscarRestaurantesPresenter
                .buscarRestaurantesOutputEmRestaurantesResponse(buscarRestaurantesOutputList);

        return ResponseEntity.ok(restauranteResponseList);
    }

}
