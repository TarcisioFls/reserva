package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ListarTodosProprietariosUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.ListarTodosProprietariosAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietarios")
public class ListarTodosProprietarioController {

    private final ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;
    private final ProprietarioPresenter proprietarioPresenter;
    private final ProprietarioRepository proprietarioRepository;


    @GetMapping
    public ResponseEntity<PagedModel<ProprietarioResponse>> listarTodos(int pagina, int tamanho) {
        var todosProprietariosUserCase = new ListarTodosProprietariosUserCase(
                new ListarTodosProprietariosAdapter(
                        proprietarioRepository, proprietarioPresenter
                ), listarTodosProprietariosPresenter
        );

        var todosProprietariosOutput = todosProprietariosUserCase.listarTodos(pagina, tamanho);
        var proprietarioResponses = listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(todosProprietariosOutput);

        return ResponseEntity.ok(proprietarioResponses);
    }

}
