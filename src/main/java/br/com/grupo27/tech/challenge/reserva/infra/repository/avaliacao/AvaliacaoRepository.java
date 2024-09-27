package br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.web.PagedModel;


import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends MongoRepository<AvaliacaoModel, String> {

    Page<AvaliacaoModel> findByReservaId(String reservaId,  Pageable pageable);
}
