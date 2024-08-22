package br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;

import java.util.Optional;

public interface CriarProprietarioGateway {

    Proprietario criar(Proprietario proprietario);

    Optional<Proprietario> buscaPorEmail(String email);
}
