package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import org.springframework.data.web.PagedModel;

public interface ListarReservasPorRestauranteIdGateway {

    PagedModel<Reserva> listarPorRestauranteId(ListarReservasPorRestauranteIdInput listarReservasPorRestauranteIdInput);

}
