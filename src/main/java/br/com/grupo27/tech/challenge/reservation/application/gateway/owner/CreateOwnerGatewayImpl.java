package br.com.grupo27.tech.challenge.reservation.application.gateway.owner;

import br.com.grupo27.tech.challenge.reservation.domain.entity.Owner;
import br.com.grupo27.tech.challenge.reservation.domain.presenters.owner.CreateOwnerPresenter;
import br.com.grupo27.tech.challenge.reservation.infra.model.OwnerModel;
import br.com.grupo27.tech.challenge.reservation.infra.repository.owner.OwnerRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateOwnerGatewayImpl implements CreateOwnerGateway {

    private final OwnerRepository ownerRepository;
    private final CreateOwnerPresenter createOwnerPresenter;

    @Override
    public Owner create(Owner owner) {
        var ownerModel = createOwnerPresenter.ownerToOwnerModel(owner);
        ownerRepository.save(ownerModel);
        owner = createOwnerPresenter.ownerModelToOwner(ownerModel);

        return owner;
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerRepository.findByEmail(email).map(createOwnerPresenter::ownerModelToOwner);
//        var ownerModel = ownerRepository.findByEmail(email);
//        var owner = createOwnerPresenter.ownerModelToOwner(ownerModel.orElse(new OwnerModel()));
//
//        return Optional.ofNullable(owner.getId() != null ? owner : null);
    }

}
