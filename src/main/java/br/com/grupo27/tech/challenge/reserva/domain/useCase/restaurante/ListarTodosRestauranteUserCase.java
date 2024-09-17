package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.ListarTodosRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.ListarTodosRestaurentesOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Getter
@RequiredArgsConstructor
public class ListarTodosRestauranteUserCase {

    private final ListarTodosRestauranteGateway listarTodosRestauranteGateway;
    private final ListarTodosRestaurantePresenter listarTodosRestaurantePresenter;

    public PagedModel<ListarTodosRestaurentesOutput> listarTodos(int pagina, int tamanho) {

        var restaurantes = listarTodosRestauranteGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return listarTodosRestaurantePresenter.pageRestauranteEmPageTodosRestauranteOutput(restaurantes);
    }

}
