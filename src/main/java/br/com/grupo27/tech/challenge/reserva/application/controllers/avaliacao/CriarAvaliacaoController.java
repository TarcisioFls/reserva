package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request.CriarAvaliacaoRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.CriarAvaliacaoUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class CriarAvaliacaoController {

    private final CriarAvaliacaoUserCaseFactory criarAvaliacaoUserCaseFactory;
    private final CriarAvaliacaoPresenter criarAvaliacaoPresenter;
    private final AvaliacaoPresenter avaliacaoPresenter;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> criar(@RequestBody CriarAvaliacaoRequest request) {

        var criarAvaliacaoUserCase = criarAvaliacaoUserCaseFactory.buildCriarAvaliacaoUserCase(
                criarAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository, reservaRepository, reservaPresenter);
        var criarAvaliacaoInput = criarAvaliacaoPresenter.criarAvaliacaoRequestEmCriarAvaliacaoInput(request);
        var criarAvaliacaoOutput = criarAvaliacaoUserCase.criar(criarAvaliacaoInput);
        var avaliacaoResponse = criarAvaliacaoPresenter.criarAvaliacaoOutputEmAvaliacaoResponse(criarAvaliacaoOutput);

        return ResponseEntity.ok(avaliacaoResponse);
    }
}
