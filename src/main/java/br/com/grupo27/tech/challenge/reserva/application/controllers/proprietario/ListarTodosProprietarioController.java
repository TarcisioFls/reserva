package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioListResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.TodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.TodosProprietariosUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.TodosProprietariosAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietarios")
public class ListarTodosProprietarioController {

    private final TodosProprietariosPresenter todosProprietariosPresenter;
    private final ProprietarioPresenter proprietarioPresenter;
    private final ProprietarioRepository proprietarioRepository;


    @GetMapping
    public ResponseEntity<ProprietarioListResponse> listarTodos(int pagina, int tamanho) {
        var todosProprietariosUserCase = new TodosProprietariosUserCase(
                new TodosProprietariosAdapter(
                        proprietarioRepository, proprietarioPresenter
                ), todosProprietariosPresenter
        );

        var todosProprietariosOutput = todosProprietariosUserCase.listarTodos(pagina, tamanho);
        var proprietarioListResponse = todosProprietariosPresenter.todosProprietariosOutputEmProprietarioListResponse(todosProprietariosOutput);

        return ResponseEntity.ok(proprietarioListResponse);
    }

}
