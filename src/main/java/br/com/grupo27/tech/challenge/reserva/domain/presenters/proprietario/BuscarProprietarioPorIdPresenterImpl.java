package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.BuscarProprietarioPorIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarProprietarioPorIdPresenterImpl implements BuscarProprietarioPorIdPresenter {

    private final ModelMapper mapper;

    @Override
    public BuscarProprietarioPorIdOutput proprietarioEmBuscarProprietarioPorIdOutput(Proprietario proprietario) {

        return mapper.map(proprietario, BuscarProprietarioPorIdOutput.class);
    }

    @Override
    public ProprietarioResponse proprietarioResponseEmBuscarProprietarioPorIdOutput(BuscarProprietarioPorIdOutput buscarProprietarioPorIdOutput) {

        return mapper.map(buscarProprietarioPorIdOutput, ProprietarioResponse.class);
    }
}
