package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public interface RestaurantePresenter {

    RestauranteModel restauranteParaRestauranteModel(Restaurante restaurante);

    Restaurante restauranteModelParaRestaurante(RestauranteModel restauranteModel);

    PagedModel<Restaurante> pageRestauranteModelListEmPageRestauranteList(Page<RestauranteModel> pageRestauranteModel);
}
