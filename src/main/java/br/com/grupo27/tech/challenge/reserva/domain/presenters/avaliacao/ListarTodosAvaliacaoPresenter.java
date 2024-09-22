package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarTodosAvaliacaoOutput;
import org.springframework.data.web.PagedModel;

public interface ListarTodosAvaliacaoPresenter {

    PagedModel<ListarTodosAvaliacaoOutput> pageAvaliacaoEmPageTodosAvaliacaoOutput(PagedModel<Avaliacao> pageAvaliacao);

    PagedModel<AvaliacaoResponse> pageTodosAvaliacaoOutputEmPageAvaliacaoListResponse(PagedModel<ListarTodosAvaliacaoOutput> todosAvaliacaoOutput);
}
