package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.ListarTodosProprietariosOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarTodosProprietariosPresenterImpl implements ListarTodosProprietariosPresenter {

    private final ModelMapper mapper;

    @Override
    public PagedModel<ListarTodosProprietariosOutput> pageProprietarioEmPageTodosProprietariosOutput(PagedModel<Proprietario> paginacaoProprietario) {

        var listarTodosProprietariosOutputList = paginacaoProprietario.getContent().stream()
                .map(proprietario -> mapper.map(proprietario, ListarTodosProprietariosOutput.class))
                .toList();

        var metadata = paginacaoProprietario.getMetadata();
        var pageListarTodosProprietariosOutput = new PageImpl<>(listarTodosProprietariosOutputList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageListarTodosProprietariosOutput);
    }

    @Override
    public PagedModel<ProprietarioResponse> pageTodosProprietariosOutputEmPageProprietarioListResponse(PagedModel<ListarTodosProprietariosOutput> todosProprietariosOutput) {
        var proprietarioResponseList = todosProprietariosOutput.getContent().stream()
                .map(proprietarioOutput -> mapper.map(proprietarioOutput, ProprietarioResponse.class))
                .toList();
        var metadata = todosProprietariosOutput.getMetadata();
        var pageProprietarioResponse = new PageImpl<>(proprietarioResponseList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageProprietarioResponse);
    }
}
