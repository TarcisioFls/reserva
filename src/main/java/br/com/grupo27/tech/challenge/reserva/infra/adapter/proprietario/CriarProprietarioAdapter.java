package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.CriarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CriarProprietarioAdapter implements CriarProprietarioGateway {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioPresenter proprietarioPresenter;

    @Override
    public Proprietario criar(Proprietario proprietario) {
        var proprietarioModel = proprietarioPresenter.proprietarioEmProprietarioModel(proprietario);
        proprietarioRepository.save(proprietarioModel);
        proprietario = proprietarioPresenter.proprietarioModelEmProprietario(proprietarioModel);

        return proprietario;
    }

    @Override
    public Optional<Proprietario> buscaPorEmail(String email) {

        return proprietarioRepository.findByEmail(email).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }

    @Override
    public Optional<Proprietario> buscaPorCpf(String cpf) {

        return proprietarioRepository.findByCpf(cpf).map(proprietarioPresenter::proprietarioModelEmProprietario);
    }

}
