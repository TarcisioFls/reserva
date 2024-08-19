package br.com.grupo27.tech.challenge.reservation.domain.presenters.owner;

import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response.OwnerResponse;
import br.com.grupo27.tech.challenge.reservation.domain.output.CreateOwnerOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OwnerResponsePresenterImpl implements OwnerResponsePresenter {

    private final ModelMapper mapper;

    @Override
    public OwnerResponse createOwnerOutputToOwnerResponse(CreateOwnerOutput createOwnerOutput) {

        return mapper.map(createOwnerOutput, OwnerResponse.class);
    }
}
