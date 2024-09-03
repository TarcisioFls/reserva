package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioListResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.TodosProprietariosOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodosProprietariosPresenterImpl implements TodosProprietariosPresenter {

    private final ModelMapper mapper;

    @Override
    public TodosProprietariosOutput pageProprietarioEmTodosProprietariosOutput(Page<Proprietario> paginacaoProprietario) {

        return new TodosProprietariosOutput(paginacaoProprietario.getTotalElements(), paginacaoProprietario.getContent(), paginacaoProprietario.getPageable());
    }

    @Override
    public ProprietarioListResponse todosProprietariosOutputEmProprietarioListResponse(TodosProprietariosOutput todosProprietariosOutput) {

        return mapper.map(todosProprietariosOutput, ProprietarioListResponse.class);
    }
}
