package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.ListarTodosRestauranteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.ListarTodosRestauranteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class ListarTodosRestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;
    private final ListarTodosRestaurantePresenter listarTodosRestaurantePresenter;


    @GetMapping
    ResponseEntity<PagedModel<RestauranteResponse>> listarTodos(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "50") int tamanho) {

        var listarTodosRestauranteUserCase = new ListarTodosRestauranteUserCase(
                new ListarTodosRestauranteAdapter(
                        restauranteRepository, restaurantePresenter), listarTodosRestaurantePresenter
        );

        var todosRestaurantesOutput = listarTodosRestauranteUserCase.listarTodos(pagina, tamanho);
        var restauranteResponses = listarTodosRestaurantePresenter.pageTodosRestaurantesOutputEmPageRestauranteResponse(todosRestaurantesOutput);

        return ResponseEntity.ok(restauranteResponses);

    }

}
