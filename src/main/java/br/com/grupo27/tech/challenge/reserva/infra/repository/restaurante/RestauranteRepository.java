package br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante;

import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestauranteRepository extends MongoRepository<RestauranteModel, String> {

}
