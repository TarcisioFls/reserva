package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.CriarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.CriarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adpter.proprietario.CriarProprietarioAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proprietarios")
public class CriarProprietarioController {

    private final CriarProprietarioUserCase criarProprietarioUserCase;
    private final CriarProprietarioPresenter criarProprietarioPresenter;
    private final ProprietarioPresenter proprietarioPresenter;

    @Autowired
    public CriarProprietarioController(ProprietarioRepository proprietarioRepository, CriarProprietarioPresenter criarProprietarioPresenter,
                                       ProprietarioPresenter proprietarioPresenter) {
        this.criarProprietarioUserCase = new CriarProprietarioUserCase(new CriarProprietarioAdapter(proprietarioRepository, proprietarioPresenter), criarProprietarioPresenter);
        this.criarProprietarioPresenter = criarProprietarioPresenter;
        this.proprietarioPresenter = proprietarioPresenter;
    }

    @PostMapping
    public ResponseEntity<ProprietarioResponse> criar(@RequestBody CriarProprietarioRequest request) {
        var criarProprietarioInput = criarProprietarioPresenter.criarProprietarioEmCriarProprietarioInput(request);
        var criarProprietarioOutput = criarProprietarioUserCase.criar(criarProprietarioInput);
        var proprietarioResponse = criarProprietarioPresenter.criarProprietarioOutputEmProprietarioResponse(criarProprietarioOutput);

        return ResponseEntity.ok(proprietarioResponse);
    }
}