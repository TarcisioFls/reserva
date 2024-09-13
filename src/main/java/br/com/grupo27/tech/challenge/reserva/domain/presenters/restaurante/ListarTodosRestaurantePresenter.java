package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.ListarTodosRestaurentesOutput;
import org.springframework.data.web.PagedModel;

public interface ListarTodosRestaurantePresenter {

    PagedModel<ListarTodosRestaurentesOutput> pageRestauranteEmPageTodosRestauranteOutput(PagedModel<Restaurante> restaurantes);

    PagedModel<RestauranteResponse> pageTodosRestaurantesOutputEmPageRestauranteResponse(PagedModel<ListarTodosRestaurentesOutput> todosRestaurantesOutput);
}
