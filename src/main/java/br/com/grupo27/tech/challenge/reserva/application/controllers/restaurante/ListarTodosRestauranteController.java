package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.ListarTodosRestauranteUserCaseFactory;
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

    private final ListarTodosRestauranteUserCaseFactory listarTodosRestauranteUserCaseFactory;
    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;
    private final ListarTodosRestaurantePresenter listarTodosRestaurantePresenter;


    @GetMapping
    ResponseEntity<PagedModel<RestauranteResponse>> listarTodos(@RequestParam int pagina
            , @RequestParam(defaultValue = "50") int tamanho) {

        var listarTodosRestauranteUserCase = listarTodosRestauranteUserCaseFactory
                .buildListarTodosRestauranteUserCase(listarTodosRestaurantePresenter, restaurantePresenter, restauranteRepository);

        var todosRestaurantesOutput = listarTodosRestauranteUserCase.listarTodos(pagina, tamanho);
        var restauranteResponses = listarTodosRestaurantePresenter
                .pageTodosRestaurantesOutputEmPageRestauranteResponse(todosRestaurantesOutput);

        return ResponseEntity.ok(restauranteResponses);

    }

}
