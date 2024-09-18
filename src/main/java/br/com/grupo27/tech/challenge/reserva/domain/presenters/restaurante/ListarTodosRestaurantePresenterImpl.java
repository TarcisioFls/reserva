package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.ListarTodosRestaurentesOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarTodosRestaurantePresenterImpl implements ListarTodosRestaurantePresenter {

    private final ModelMapper mapper;

    @Override
    public PagedModel<ListarTodosRestaurentesOutput> pageRestauranteEmPageTodosRestauranteOutput(PagedModel<Restaurante> paginacaoRestaurante) {
        var listarTodosRestaurentesOutputList = paginacaoRestaurante.getContent().stream()
                .map(restaurante -> mapper.map(restaurante, ListarTodosRestaurentesOutput.class))
                .toList();

        var metadata = paginacaoRestaurante.getMetadata();
        var listarTodosRestaurentesOutputs = new PageImpl<>(listarTodosRestaurentesOutputList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(listarTodosRestaurentesOutputs);
    }

    @Override
    public PagedModel<RestauranteResponse> pageTodosRestaurantesOutputEmPageRestauranteResponse(PagedModel<ListarTodosRestaurentesOutput> todosRestaurantesOutput) {
        var restauranteList = todosRestaurantesOutput.getContent().stream()
                .map(restauranteOutput -> mapper.map(restauranteOutput, RestauranteResponse.class))
                .toList();

        var metadata = todosRestaurantesOutput.getMetadata();
        var pageRestauranteResponse = new PageImpl<>(restauranteList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageRestauranteResponse);
    }
}
