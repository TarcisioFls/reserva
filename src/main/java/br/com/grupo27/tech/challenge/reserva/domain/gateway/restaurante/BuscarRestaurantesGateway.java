package br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import org.springframework.data.web.PagedModel;

public interface BuscarRestaurantesGateway {

    PagedModel<Restaurante> buscar(String buscar);
}
