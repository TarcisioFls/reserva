package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorClientIdAdapter implements BuscarReservasPorClientIdGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public List<Reserva> buscarPorClientIdEStatusReservadoEDataHora(Reserva reserva, ReservaStatus status) {
        var inicioDoDia = reserva.getDataHora().toLocalDate().atStartOfDay();
        var fimDoDia = reserva.getDataHora().toLocalDate().atTime(LocalTime.MAX);
        var reservaList = reservaRepository.findByClienteIdAndStatusAndDataHoraBetween(reserva.getClienteId(), RESERVADO, inicioDoDia, fimDoDia);

        return reservaList.stream().map(reservaPresenter::reservaModelEmReserva).toList();
    }
}
