package br.com.grupo27.tech.challenge.reserva.infra.repository.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends MongoRepository<ReservaModel, String> {
    Optional<List<ReservaModel>> findByRestauranteIdAndStatusAndDataHoraBetween(String restauranteId, ReservaStatus status, LocalDateTime inicioDoDia, LocalDateTime fimDoDia);

    List<ReservaModel> findByClienteIdAndStatusAndDataHoraBetween(String clienteId, ReservaStatus status, LocalDateTime inicioDoDia, LocalDateTime fimDoDia);

    Page<ReservaModel> findByRestauranteId(String restauranteId, PageRequest pageRequest);

    Page<ReservaModel> findByClienteId(String clienteId, PageRequest pageRequest);
}
