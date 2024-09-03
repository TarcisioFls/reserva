package br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TodosProprietariosGateway {

    Page<Proprietario> listarTodos(PageRequest pageRequest);

}
