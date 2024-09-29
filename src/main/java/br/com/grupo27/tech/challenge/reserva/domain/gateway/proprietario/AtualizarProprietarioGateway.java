package br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;

import java.util.Optional;

public interface AtualizarProprietarioGateway {

    Proprietario atualizar(Proprietario proprietario);

    Optional<Proprietario> buscarPorId(String id);

    Optional<Proprietario> buscarPorEmail(String email);

    Optional<Proprietario> buscarPorCpf(String cpf);
}
