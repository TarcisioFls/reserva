package br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

public interface ListarTodosProprietariosGateway {

    PagedModel<Proprietario> listarTodos(PageRequest pageRequest);

}
