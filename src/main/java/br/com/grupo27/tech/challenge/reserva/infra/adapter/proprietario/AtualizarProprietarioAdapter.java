package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.AtualizarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AtualizarProprietarioAdapter implements AtualizarProprietarioGateway {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioPresenter proprietarioPresenter;

    @Override
    public Proprietario atualizar(Proprietario proprietario) {
        var proprietarioModel = proprietarioPresenter.proprietarioEmProprietarioModel(proprietario);
        proprietarioRepository.save(proprietarioModel);

        return proprietarioPresenter.proprietarioModelEmProprietario(proprietarioModel);
    }

    @Override
    public Optional<Proprietario> buscarPorId(String id) {

        return proprietarioRepository.findById(id).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }

    @Override
    public Optional<Proprietario> buscarPorEmail(String email) {

        return proprietarioRepository.findByEmail(email).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }

    @Override
    public Optional<Proprietario> buscarPorCpf(String cpf) {
        return proprietarioRepository.findByCpf(cpf).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }
}
