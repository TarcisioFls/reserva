package br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

public interface ListarTodosRestauranteGateway {

    PagedModel<Restaurante> listarTodos(PageRequest pageRequest);
}
