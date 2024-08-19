package br.com.grupo27.tech.challenge.reservation.domain.presenters.owner;

import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response.OwnerResponse;
import br.com.grupo27.tech.challenge.reservation.domain.output.CreateOwnerOutput;

public interface OwnerResponsePresenter {

    OwnerResponse createOwnerOutputToOwnerResponse(CreateOwnerOutput createOwnerOutput);

}
