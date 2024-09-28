package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarTodosAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarTodosAvaliacaoOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Getter
@RequiredArgsConstructor
public class ListarTodosAvaliacaoUserCase {

    private final ListarTodosAvaliacaoGateway listarTodosAvaliacaoGateway;
    private final ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter;

    public PagedModel<ListarTodosAvaliacaoOutput> listarTodos(int pagina, int tamanho) {

        var paginacaoAvaliacao = listarTodosAvaliacaoGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return listarTodosAvaliacaoPresenter.pageAvaliacaoEmPageTodosAvaliacaoOutput(paginacaoAvaliacao);
    }
}
