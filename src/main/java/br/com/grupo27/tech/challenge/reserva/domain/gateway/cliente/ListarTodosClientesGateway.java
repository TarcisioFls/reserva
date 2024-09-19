package br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

public interface ListarTodosClientesGateway {

    PagedModel<Cliente> listarTodos(PageRequest pageRequest);
}
