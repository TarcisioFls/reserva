package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarAvalicaoPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarAvaliacaoPorRestauranteIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;

@Getter
@RequiredArgsConstructor
public class ListarAvaliacaoPorRestauranteIdUserCase {

    private final ListarAvalicaoPorRestauranteIdGateway listarAvalicaoPorRestauranteIdGateway;

     private final ListarAvaliacaoPorRestauranteIdPresenter listarAvaliacaoPorRestauranteIdPresenter;

    public PagedModel<ListarAvaliacaoPorRestauranteIdOutput> listarPorRestauranteId(String id, int pagina, int tamanho){

        var paginacaoAvaliacao = listarAvalicaoPorRestauranteIdGateway.listarPorRestaurante(id, pagina, tamanho);


        return listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(paginacaoAvaliacao);
    }
}
