package br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

public interface ListarTodosAvaliacaoGateway {

    PagedModel<Avaliacao> listarTodos(PageRequest pageRequest);
}
