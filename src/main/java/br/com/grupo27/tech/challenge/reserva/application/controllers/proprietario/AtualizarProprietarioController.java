package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.UserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietarios")
public class AtualizarProprietarioController {

    private final UserCaseFactory userCaseFactory;
    private final AtualizarProprietarioPresenter atualizarProprietarioPresenter;
    private final ProprietarioPresenter proprietarioPresenter;
    private final ProprietarioRepository proprietarioRepository;

    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioResponse> atualizar(@PathVariable String id, @RequestBody AtualizarProprietarioRequest request) {

        var atualizarProprietarioUserCase = userCaseFactory.buildAtualizarProprietarioUserCase(atualizarProprietarioPresenter, proprietarioPresenter, proprietarioRepository);
        var atualizarProprietarioInput = atualizarProprietarioPresenter.atualizarProprietarioRequestEmAtualizarProprietarioInput(id, request);
        var atualizarProprietarioOutput = atualizarProprietarioUserCase.atualizar(atualizarProprietarioInput);
        var proprietarioResponse = atualizarProprietarioPresenter.atualizarProprietarioOutputEmProprietarioResponse(atualizarProprietarioOutput);

        return ResponseEntity.ok(proprietarioResponse);
    }

}
