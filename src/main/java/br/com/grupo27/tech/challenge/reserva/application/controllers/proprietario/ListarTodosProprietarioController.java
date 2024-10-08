package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.ListarTodosProprietariosUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietarios")
public class ListarTodosProprietarioController {

    private final ListarTodosProprietariosUserCaseFactory listarTodosProprietariosUserCaseFactory;
    private final ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;
    private final ProprietarioPresenter proprietarioPresenter;
    private final ProprietarioRepository proprietarioRepository;


    @GetMapping
    public ResponseEntity<PagedModel<ProprietarioResponse>> listarTodos(@RequestParam(defaultValue = "0") int pagina,@RequestParam(defaultValue = "50") int tamanho) {

        var listarTodosProprietariosUserCase = listarTodosProprietariosUserCaseFactory.buildListarTodosProprietariosUserCase(
                listarTodosProprietariosPresenter, proprietarioPresenter, proprietarioRepository
        );

        var todosProprietariosOutput = listarTodosProprietariosUserCase.listarTodos(pagina, tamanho);
        var proprietarioResponses = listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(todosProprietariosOutput);

        return ResponseEntity.ok(proprietarioResponses);
    }

}
