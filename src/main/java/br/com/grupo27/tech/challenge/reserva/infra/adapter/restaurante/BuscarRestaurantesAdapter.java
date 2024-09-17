package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantesGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantesAdapter implements BuscarRestaurantesGateway {

    public static final int NUMERO_DA_PAGINA = 0;
    public static final int TAMANHO_DA_PAGINA = 50;
    private final RestauranteRepository restauranteRepository;

    private final RestaurantePresenter restaurantePresenter;

    @Override
    public PagedModel<Restaurante> buscar(String buscar) {

        var pageRequest = PageRequest.of(NUMERO_DA_PAGINA, TAMANHO_DA_PAGINA);

        var restauranteModelList = restauranteRepository.findByNomeOrLocalizacaoOrTipoCozinhaList(buscar, pageRequest)
                .orElseGet(Page::empty);

        return restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(restauranteModelList);

    }
}
