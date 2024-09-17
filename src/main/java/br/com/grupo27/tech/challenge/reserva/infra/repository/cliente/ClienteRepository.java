package br.com.grupo27.tech.challenge.reserva.infra.repository.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<ClienteModel, Long> {
    Optional<ClienteModel> findByEmail(String email);

    Optional<ClienteModel> findByCpf(String cpf);

    Optional<ClienteModel> findById(String id);
}
