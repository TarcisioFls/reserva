package br.com.grupo27.tech.challenge.reservation.application.gateway.owner;

import br.com.grupo27.tech.challenge.reservation.domain.entity.Owner;

import java.util.Optional;

public interface CreateOwnerGateway {

    Owner create(Owner owner);

    Optional<Owner> findByEmail(String email);
}
