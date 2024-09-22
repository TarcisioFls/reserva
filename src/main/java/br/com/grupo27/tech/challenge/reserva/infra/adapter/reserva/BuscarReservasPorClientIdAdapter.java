package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorClientIdAdapter implements BuscarReservasPorClientIdGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public List<Reserva> buscarPorClientIdEDataHora(Reserva reserva) {
        var inicioDoDia = reserva.getDataHora().toLocalDate().atStartOfDay();
        var fimDoDia = reserva.getDataHora().toLocalDate().atTime(LocalTime.MAX);
        var reservaList = reservaRepository.findByClienteIdAndDataHoraBetween(reserva.getClienteId(), inicioDoDia, fimDoDia);

        return reservaList.stream().map(reservaPresenter::reservaModelEmReserva).toList();
    }
}
