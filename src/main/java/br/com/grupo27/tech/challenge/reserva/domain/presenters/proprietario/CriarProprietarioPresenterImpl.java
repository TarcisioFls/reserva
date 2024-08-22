package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.CriarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.CriarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.CriarProprietarioOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarProprietarioPresenterImpl implements CriarProprietarioPresenter {

    private final ModelMapper mapper;

    @Override
    public Proprietario criarProprietarioInputEmProprietario(CriarProprietarioInput criarProprietarioInput) {

        return new Proprietario(criarProprietarioInput.getId(), criarProprietarioInput.getNome(), criarProprietarioInput.getEmail(),
                criarProprietarioInput.getPassword(), criarProprietarioInput.getTelefone());
    }

    @Override
    public CriarProprietarioOutput proprietarioEmCriarProprietarioOutput(Proprietario proprietario) {

        return mapper.map(proprietario, CriarProprietarioOutput.class);
    }

    @Override
    public CriarProprietarioInput criarProprietarioEmCriarProprietarioInput(CriarProprietarioRequest request) {

        return mapper.map(request, CriarProprietarioInput.class);
    }

    @Override
    public ProprietarioResponse criarProprietarioOutputEmProprietarioResponse(CriarProprietarioOutput criarProprietarioOutput) {

        return mapper.map(criarProprietarioOutput, ProprietarioResponse.class);
    }

}
