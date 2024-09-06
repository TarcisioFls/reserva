package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantePresenterImpl implements RestaurantePresenter {

    private final ModelMapper mapper;

    @Override
    public RestauranteModel restauranteParaRestauranteModel(Restaurante restaurante) {

        return mapper.map(restaurante, RestauranteModel.class);
    }

    @Override
    public Restaurante restauranteModelParaRestaurante(RestauranteModel restauranteModel) {
        return mapper.map(restauranteModel, Restaurante.class);
    }
}
