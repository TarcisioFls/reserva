package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioListResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.TodosProprietariosOutput;
import org.springframework.data.domain.Page;

public interface TodosProprietariosPresenter {

    TodosProprietariosOutput pageProprietarioEmTodosProprietariosOutput(Page<Proprietario> paginacaoProprietario);

    ProprietarioListResponse todosProprietariosOutputEmProprietarioListResponse(TodosProprietariosOutput todosProprietariosOutput);
}
