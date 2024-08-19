package br.com.grupo27.tech.challenge.reservation.domain.presenters.owner;

import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.request.CreateOwnerRequest;
import br.com.grupo27.tech.challenge.reservation.domain.entity.Owner;
import br.com.grupo27.tech.challenge.reservation.domain.input.CreateOwnerInput;
import br.com.grupo27.tech.challenge.reservation.domain.output.CreateOwnerOutput;
import br.com.grupo27.tech.challenge.reservation.infra.model.OwnerModel;

public interface CreateOwnerPresenter {

    Owner createOwnerInputToOwner(CreateOwnerInput createOwnerInput);

    CreateOwnerOutput ownerToCreateOwnerOutput(Owner owner);

    OwnerModel ownerToOwnerModel(Owner owner);

    Owner ownerModelToOwner(OwnerModel ownerModel);

    CreateOwnerInput createOwnerRequestToCreateOwnerInput(CreateOwnerRequest request);
}
