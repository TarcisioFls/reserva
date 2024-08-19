package br.com.grupo27.tech.challenge.reservation.domain.presenters.owner;

import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.request.CreateOwnerRequest;
import br.com.grupo27.tech.challenge.reservation.domain.entity.Owner;
import br.com.grupo27.tech.challenge.reservation.domain.input.CreateOwnerInput;
import br.com.grupo27.tech.challenge.reservation.domain.output.CreateOwnerOutput;
import br.com.grupo27.tech.challenge.reservation.infra.model.OwnerModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOwnerPresenterImpl implements CreateOwnerPresenter {

    private final ModelMapper mapper;

    @Override
    public Owner createOwnerInputToOwner(CreateOwnerInput createOwnerInput) {

        return mapper.map(createOwnerInput, Owner.class);
    }

    @Override
    public CreateOwnerOutput ownerToCreateOwnerOutput(Owner owner) {

        return mapper.map(owner, CreateOwnerOutput.class);
    }

    @Override
    public OwnerModel ownerToOwnerModel(Owner owner) {

        return mapper.map(owner, OwnerModel.class);
    }

    @Override
    public Owner ownerModelToOwner(OwnerModel ownerModel) {

        return mapper.map(ownerModel, Owner.class);
    }

    @Override
    public CreateOwnerInput createOwnerRequestToCreateOwnerInput(CreateOwnerRequest request) {

        return mapper.map(request, CreateOwnerInput.class);
    }
}
