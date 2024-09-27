package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorClienteIdInput;
import org.springframework.data.web.PagedModel;

public interface ListarReservasPorClienteIdGateway {

    PagedModel<Reserva> listarPorClienteId(ListarReservasPorClienteIdInput listarReservasPorClienteIdInput);

}
