package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.ListarTodosRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarTodosRestauranteAdapter implements ListarTodosRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;

    @Override
    public PagedModel<Restaurante> listarTodos(PageRequest paginacaoRequest) {

        var pageRestauranteModel = restauranteRepository.findAll(paginacaoRequest);

        return restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(pageRestauranteModel);
    }
}
