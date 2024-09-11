package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantePresenterImpl implements RestaurantePresenter {

    private final ModelMapper mapper;

    @Override
    public RestauranteModel restauranteParaRestauranteModel(Restaurante restaurante) {

        return new RestauranteModel(restaurante.getId(), restaurante.getNome(), restaurante.getDescricao(),
                restaurante.getLocalizacao(), restaurante.getHoraAbertura(), restaurante.getHoraFechamento(),
                restaurante.getCapacidade(), restaurante.getTipoCozinhaList(), restaurante.getProprietarioId());
    }

    @Override
    public Restaurante restauranteModelParaRestaurante(RestauranteModel restauranteModel) {
        return new Restaurante(restauranteModel.getId(), restauranteModel.getNome(), restauranteModel.getDescricao(),
                restauranteModel.getLocalizacao(), restauranteModel.getHoraAbertura(), restauranteModel.getHoraFechamento(),
                restauranteModel.getCapacidade(), restauranteModel.getTipoCozinhaList(), restauranteModel.getProprietarioId());
    }

    @Override
    public PagedModel<Restaurante> pageRestauranteModelListEmPageRestauranteList(Page<RestauranteModel> pageRestauranteModel) {
        var restaurantes = pageRestauranteModel.map(this::restauranteModelParaRestaurante);

        return new PagedModel<>(restaurantes);
    }

}
