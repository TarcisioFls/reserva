package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.ListarTodosProprietariosOutput;
import org.springframework.data.web.PagedModel;

public interface ListarTodosProprietariosPresenter {

    PagedModel<ListarTodosProprietariosOutput> pageProprietarioEmPageTodosProprietariosOutput(PagedModel<Proprietario> paginacaoProprietario);

    PagedModel<ProprietarioResponse> pageTodosProprietariosOutputEmPageProprietarioListResponse(PagedModel<ListarTodosProprietariosOutput> todosProprietariosOutput);
}
