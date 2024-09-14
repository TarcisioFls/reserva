package br.com.grupo27.tech.challenge.reserva.mock.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.ListarTodosProprietariosOutput;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ListarTodosProprietariosDados {

    static PagedModel<ListarTodosProprietariosOutput> getPageTodosProprietariosOutput() {
        var proprietarioJoao = new ListarTodosProprietariosOutput("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
        var proprietarioMaria = new ListarTodosProprietariosOutput("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
        var todosProprietariosOutputList = List.of(proprietarioJoao, proprietarioMaria);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodosProrietariosOutput = new PageImpl<>(todosProprietariosOutputList, pageRequest, todosProprietariosOutputList.size());

        return new PagedModel<>(pageTodosProrietariosOutput);
    }

    static PagedModel<ProprietarioResponse> getPageProprietariosResponse() {
        var proprietarioJoaoResponse = new ProprietarioResponse("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
        var proprietarioMariaResponse = new ProprietarioResponse("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
        var proprietarioResponseList = List.of(proprietarioJoaoResponse, proprietarioMariaResponse);
        var pageRequest = PageRequest.of(0, 10);

        var pageProprietarioResponse = new PageImpl<>(proprietarioResponseList, pageRequest, proprietarioResponseList.size());

        return new PagedModel<>(pageProprietarioResponse);
    }

    static PagedModel<Proprietario> getPageProprietario() {

        var proprietarioJoao = new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
        var proprietarioMaria = new Proprietario("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
        var pageRequest = PageRequest.of(0, 10);
        var proprietarios = List.of(proprietarioJoao, proprietarioMaria);

        var pageProprietarios = new PageImpl<>(proprietarios, pageRequest, proprietarios.size());

        return new PagedModel<>(pageProprietarios);
    }
}
