package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

public interface ListarTodasReservasGateway {

    PagedModel<Reserva> listarTodos(PageRequest pageRequest);

}
