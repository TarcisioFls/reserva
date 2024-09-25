package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorRestauranteIdAdapter implements BuscarReservasPorRestauranteIdGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public Optional<List<Reserva>> buscarPorRestauranteIdEStatusReservadoEDataHora(String restauranteId, ReservaStatus status, LocalDateTime horaData) {

        var inicioDoDia = horaData.toLocalDate().atStartOfDay();
        var fimDoDia = horaData.toLocalDate().atTime(LocalTime.MAX);
        var reservaModelList = reservaRepository.findByRestauranteIdAndStatusAndDataHoraBetween(restauranteId, status, inicioDoDia, fimDoDia);

        return reservaModelList.map(reservaModels -> reservaModels.stream()
                .map(reservaPresenter::reservaModelEmReserva)
                .toList());
    }
}
