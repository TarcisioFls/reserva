package br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;

import java.util.Optional;

public interface BuscarProprietarioPorIdGateway {

    Optional<Proprietario> buscarPorId(String id);

}
