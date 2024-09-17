package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarProprietarioPorIdAdapter implements BuscarProprietarioPorIdGateway {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioPresenter proprietarioPresenter;

    @Override
    public Optional<Proprietario> buscarPorId(String id) {

        return proprietarioRepository.findById(id).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }

}
