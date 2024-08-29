package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;

public interface RestaurantePresenter {

    RestauranteModel restauranteParaRestauranteModel(Restaurante restaurante);

    Restaurante restauranteModelParaRestaurante(RestauranteModel restauranteModel);
}
