package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarProprietarioPresenterImpl implements AtualizarProprietarioPresenter {

    private final ModelMapper mapper;

    public AtualizarProprietarioInput atualizarProprietarioRequestEmAtualizarProprietarioInput(String id, AtualizarProprietarioRequest atualizarProprietarioRequest) {
        var atualizarProprietarioInput = mapper.map(atualizarProprietarioRequest, AtualizarProprietarioInput.class);
        atualizarProprietarioInput.setId(id);

        return atualizarProprietarioInput;
    }

    @Override
    public Proprietario atualizarProprietarioInputEmProprietario(Proprietario proprietario, AtualizarProprietarioInput atualizarProprietarioInput) {

        return new Proprietario(proprietario.getId(), atualizarProprietarioInput.getNome(), atualizarProprietarioInput.getEmail(),
                atualizarProprietarioInput.getSenha(), atualizarProprietarioInput.getTelefone(), atualizarProprietarioInput.getCpf());
    }

    @Override
    public AtualizarProprietarioOutput proprietarioEmAtualizarProprietarioOutput(Proprietario proprietario) {

        return mapper.map(proprietario, AtualizarProprietarioOutput.class);
    }

    @Override
    public ProprietarioResponse atualizarProprietarioOutputEmProprietarioResponse(AtualizarProprietarioOutput atualizarProprietarioOutput) {

        return mapper.map(atualizarProprietarioOutput, ProprietarioResponse.class);
    }
}
