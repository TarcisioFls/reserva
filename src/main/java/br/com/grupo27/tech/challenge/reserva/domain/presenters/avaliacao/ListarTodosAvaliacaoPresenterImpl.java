package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarTodosAvaliacaoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarTodosAvaliacaoPresenterImpl implements ListarTodosAvaliacaoPresenter {

    private final ModelMapper mapper;

    @Override
    public PagedModel<ListarTodosAvaliacaoOutput> pageAvaliacaoEmPageTodosAvaliacaoOutput(PagedModel<Avaliacao> pageAvaliacao) {
        var listarTodosAvaliacaoOutputList = pageAvaliacao.getContent().stream()
                .map(avaliacao -> mapper.map(avaliacao, ListarTodosAvaliacaoOutput.class))
                .toList();

        var metadata = pageAvaliacao.getMetadata();
        var listarTodosAvaliacaoOutputs = new PageImpl<>(listarTodosAvaliacaoOutputList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(listarTodosAvaliacaoOutputs);
    }

    @Override
    public PagedModel<AvaliacaoResponse> pageTodosAvaliacaoOutputEmPageAvaliacaoListResponse(PagedModel<ListarTodosAvaliacaoOutput> todosAvaliacaoOutput) {
        var avaliacaoList = todosAvaliacaoOutput.getContent().stream()
                .map(avaliacaoOutput -> mapper.map(avaliacaoOutput, AvaliacaoResponse.class))
                .toList();

        var metadata = todosAvaliacaoOutput.getMetadata();
        var pageAvaliacaoResponse = new PageImpl<>(avaliacaoList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageAvaliacaoResponse);
    }
}