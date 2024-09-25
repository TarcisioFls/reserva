package br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvaliacaoRepository extends MongoRepository<AvaliacaoModel, String> {
}
