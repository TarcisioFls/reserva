package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarAvaliacaoPorRestauranteIdOutput;
import org.springframework.data.web.PagedModel;

public interface ListarAvaliacaoPorRestauranteIdPresenter {

    PagedModel<ListarAvaliacaoPorRestauranteIdOutput> pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(PagedModel<Avaliacao> pageAvaliacao);

    PagedModel<AvaliacaoResponse> pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(PagedModel<ListarAvaliacaoPorRestauranteIdOutput> PageListarAvaliacaoPorRestauranteIdOutput);
}
