package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProprietarioPresenterImpl implements ProprietarioPresenter {

    private final ModelMapper mapper;

    @Override
    public ProprietarioModel proprietarioEmProprietarioModel(Proprietario proprietario) {

        return mapper.map(proprietario, ProprietarioModel.class);
    }

    @Override
    public Proprietario proprietarioModelEmProprietario(ProprietarioModel proprietarioModel) {

        return mapper.map(proprietarioModel, Proprietario.class);
    }

    @Override
    public ProprietarioResponse atualizarProprietarioOutputEmProprietarioResponse(AtualizarProprietarioOutput atualizarProprietarioOutput) {

        return mapper.map(atualizarProprietarioOutput, ProprietarioResponse.class);
    }

}
