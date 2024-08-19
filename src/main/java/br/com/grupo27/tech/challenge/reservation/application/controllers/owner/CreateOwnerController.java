package br.com.grupo27.tech.challenge.reservation.application.controllers.owner;

import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.request.CreateOwnerRequest;
import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response.OwnerResponse;
import br.com.grupo27.tech.challenge.reservation.application.gateway.owner.CreateOwnerGatewayImpl;
import br.com.grupo27.tech.challenge.reservation.domain.presenters.owner.CreateOwnerPresenter;
import br.com.grupo27.tech.challenge.reservation.domain.presenters.owner.OwnerResponsePresenter;
import br.com.grupo27.tech.challenge.reservation.domain.useCase.owner.CreateOwnerUserCase;
import br.com.grupo27.tech.challenge.reservation.infra.repository.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class CreateOwnerController {

    private final CreateOwnerUserCase createOwnerUserCase;
    private final CreateOwnerPresenter createOwnerPresenter;
    private final OwnerResponsePresenter ownerResponsePresenter;

    @Autowired
    public CreateOwnerController(OwnerRepository ownerRepository, CreateOwnerPresenter createOwnerPresenter, OwnerResponsePresenter ownerResponsePresenter) {
        this.createOwnerUserCase = new CreateOwnerUserCase(new CreateOwnerGatewayImpl(ownerRepository, createOwnerPresenter), createOwnerPresenter);
        this.createOwnerPresenter = createOwnerPresenter;
        this.ownerResponsePresenter = ownerResponsePresenter;
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> create(@RequestBody CreateOwnerRequest request) {
        var createOwnerInput = createOwnerPresenter.createOwnerRequestToCreateOwnerInput(request);
        var createOwnerOutput = createOwnerUserCase.create(createOwnerInput);
        var ownerResponse = ownerResponsePresenter.createOwnerOutputToOwnerResponse(createOwnerOutput);

        return ResponseEntity.ok(ownerResponse);
    }
}