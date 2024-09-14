package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.CriarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.CriarProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietarios")
public class CriarProprietarioController {

    private final CriarProprietarioUserCaseFactory criarProprietarioUserCaseFactory;
    private final CriarProprietarioPresenter criarProprietarioPresenter;
    private final ProprietarioPresenter proprietarioPresenter;
    private final ProprietarioRepository proprietarioRepository;

    @PostMapping
    public ResponseEntity<ProprietarioResponse> criar(@RequestBody CriarProprietarioRequest request) {

        var criarProprietarioUserCase = criarProprietarioUserCaseFactory.buildCriarProprietarioUserCase(criarProprietarioPresenter, proprietarioPresenter, proprietarioRepository);

        var criarProprietarioInput = criarProprietarioPresenter.criarProprietarioEmCriarProprietarioInput(request);
        var criarProprietarioOutput = criarProprietarioUserCase.criar(criarProprietarioInput);
        var proprietarioResponse = criarProprietarioPresenter.criarProprietarioOutputEmProprietarioResponse(criarProprietarioOutput);

        return ResponseEntity.ok(proprietarioResponse);
    }
}