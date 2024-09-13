package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.ListarTodosProprietariosGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.ListarTodosProprietariosOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Getter
@RequiredArgsConstructor
public class ListarTodosProprietariosUserCase {

    private final ListarTodosProprietariosGateway listarTodosProprietariosGateway;
    private final ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;

    public PagedModel<ListarTodosProprietariosOutput> listarTodos(int pagina, int tamanho) {

        var paginacaoProprietario = listarTodosProprietariosGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return listarTodosProprietariosPresenter.pageProprietarioEmPageTodosProprietariosOutput(paginacaoProprietario);
    }

}
