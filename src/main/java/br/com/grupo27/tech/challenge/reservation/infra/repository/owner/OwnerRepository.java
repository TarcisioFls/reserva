package br.com.grupo27.tech.challenge.reservation.infra.repository.owner;

import br.com.grupo27.tech.challenge.reservation.infra.model.OwnerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository  extends MongoRepository<OwnerModel, String> {

    Optional<OwnerModel> findByEmail(String email);

}
