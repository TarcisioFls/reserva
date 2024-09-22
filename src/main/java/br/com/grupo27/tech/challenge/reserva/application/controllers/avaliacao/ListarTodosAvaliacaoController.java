package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.ListarTodosAvaliacaoUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class ListarTodosAvaliacaoController {

    private final ListarTodosAvaliacaoUserCaseFactory listarTodosAvaliacaoUserCaseFactory;
    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoPresenter avaliacaoPresenter;
    private final ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter;

    @GetMapping
    public ResponseEntity<PagedModel<AvaliacaoResponse>> listarTodos(@RequestParam(defaultValue = "0") int pagina,
                                                                     @RequestParam(defaultValue = "50") int tamanho) {

        var listarTodosAvaliacaoUserCase = listarTodosAvaliacaoUserCaseFactory
                .buildListarTodosAvaliacaoUserCase(listarTodosAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);

        var todosAvaliacaoOutput = listarTodosAvaliacaoUserCase.listarTodos(pagina, tamanho);
        var avaliacaoResponse = listarTodosAvaliacaoPresenter
                .pageTodosAvaliacaoOutputEmPageAvaliacaoListResponse(todosAvaliacaoOutput);

        return ResponseEntity.ok(avaliacaoResponse);
    }
}
