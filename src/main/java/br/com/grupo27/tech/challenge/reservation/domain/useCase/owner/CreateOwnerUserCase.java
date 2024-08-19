package br.com.grupo27.tech.challenge.reservation.domain.useCase.owner;

import br.com.grupo27.tech.challenge.reservation.application.gateway.owner.CreateOwnerGateway;
import br.com.grupo27.tech.challenge.reservation.domain.input.CreateOwnerInput;
import br.com.grupo27.tech.challenge.reservation.domain.output.CreateOwnerOutput;
import br.com.grupo27.tech.challenge.reservation.domain.presenters.owner.CreateOwnerPresenter;
import br.com.grupo27.tech.challenge.reservation.infra.config.exception.ExceptionAdvice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.com.grupo27.tech.challenge.reservation.infra.config.exception.ErrorCode.OWNER_ALREADY_EXISTS_WITH_EMAIL;

@Getter
@AllArgsConstructor
public class CreateOwnerUserCase {

    private final CreateOwnerGateway createOwnerGateway;
    private final CreateOwnerPresenter createOwnerPresenter;

    public CreateOwnerOutput create(CreateOwnerInput createOwnerInput) {
        createOwnerGateway.findByEmail(createOwnerInput.getEmail()).ifPresent(owner -> {
            throw new ExceptionAdvice(OWNER_ALREADY_EXISTS_WITH_EMAIL);

        });

        var owner =  createOwnerPresenter.createOwnerInputToOwner(createOwnerInput);
        owner = createOwnerGateway.create(owner);
        var createOwnerOutput = createOwnerPresenter.ownerToCreateOwnerOutput(owner);

        return createOwnerOutput;
    }

}
