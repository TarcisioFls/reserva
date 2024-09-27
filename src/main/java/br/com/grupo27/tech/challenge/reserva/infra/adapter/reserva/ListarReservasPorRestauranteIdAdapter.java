package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarReservasPorRestauranteIdAdapter implements ListarReservasPorRestauranteIdGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public PagedModel<Reserva> listarPorRestauranteId(ListarReservasPorRestauranteIdInput input) {
         var reservaModelPage = reservaRepository.findByRestauranteId(input.getRestauranteId(),
                PageRequest.of(input.getPagina(), input.getTamanho()));

        return reservaPresenter.pageReservaModelListEmPageReservaList(reservaModelPage);
    }
}
