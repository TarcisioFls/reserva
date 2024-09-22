package br.com.grupo27.tech.challenge.reserva.infra.repository.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends MongoRepository<ReservaModel, String> {
    Optional<List<ReservaModel>> findByRestauranteIdAndDataHoraBetween(String restauranteId, LocalDateTime inicioDoDia, LocalDateTime fimDoDia);

    List<ReservaModel> findByClienteIdAndDataHoraBetween(String clienteId, LocalDateTime inicioDoDia, LocalDateTime fimDoDia);
}
