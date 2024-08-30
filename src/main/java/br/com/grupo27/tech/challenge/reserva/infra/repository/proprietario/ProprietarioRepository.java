package br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario;

import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietarioRepository extends MongoRepository<ProprietarioModel, String> {

    Optional<ProprietarioModel> findByEmail(String email);

}
