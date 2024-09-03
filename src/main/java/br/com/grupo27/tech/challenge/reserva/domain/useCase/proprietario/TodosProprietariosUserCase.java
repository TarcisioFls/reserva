package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.TodosProprietariosGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.TodosProprietariosOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.TodosProprietariosPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Getter
@RequiredArgsConstructor
public class TodosProprietariosUserCase {

    private final TodosProprietariosGateway todosProprietariosGateway;
    private final TodosProprietariosPresenter todosProprietariosPresenter;

    public TodosProprietariosOutput listarTodos(int pagina, int tamanho) {

        var paginacaoProprietario = todosProprietariosGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return todosProprietariosPresenter.pageProprietarioEmTodosProprietariosOutput(paginacaoProprietario);
    }

}
