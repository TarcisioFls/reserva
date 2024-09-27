package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarAvaliacaoPorRestauranteIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarAvaliacaoPorRestauranteIdPresenterImpl implements ListarAvaliacaoPorRestauranteIdPresenter {

    private final ModelMapper mapper;


    @Override
    public PagedModel<ListarAvaliacaoPorRestauranteIdOutput> pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(PagedModel<Avaliacao> pageAvaliacao) {
        var listarAvaliacaoPorRestauranteIdOutputList = pageAvaliacao.getContent().stream()
                .map(avaliacao -> mapper.map(avaliacao, ListarAvaliacaoPorRestauranteIdOutput.class))
                .toList();

        var metadata = pageAvaliacao.getMetadata();
        var listarAvaliacaoPorRestauranteIdOutputs = new PageImpl<>(
                listarAvaliacaoPorRestauranteIdOutputList,
                PageRequest.of((int) requireNonNull(metadata).number(),
                (int) metadata.size()), metadata.totalElements()
        );
        return new PagedModel<>(listarAvaliacaoPorRestauranteIdOutputs);
    }

    @Override
    public PagedModel<AvaliacaoResponse> pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(PagedModel<ListarAvaliacaoPorRestauranteIdOutput> pageListarAvaliacaoPorRestauranteIdOutput) {

        var avaliacaoList = pageListarAvaliacaoPorRestauranteIdOutput.getContent().stream()
                .map(avaliacaoOutput  -> mapper.map(avaliacaoOutput  , AvaliacaoResponse.class))
                .toList();

        var metadata = pageListarAvaliacaoPorRestauranteIdOutput.getMetadata();
        var pageAvaliacaoResponse =  new PageImpl<>(avaliacaoList,
                PageRequest.of((int) requireNonNull(metadata).number(),
                (int) metadata.size()),
                metadata.totalElements());

        return new PagedModel<>(pageAvaliacaoResponse);
    }
}
