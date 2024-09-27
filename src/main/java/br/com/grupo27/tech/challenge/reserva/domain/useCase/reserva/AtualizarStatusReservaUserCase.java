package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarStatusReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarStatusReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_NAO_ENCONTRADA;

@RequiredArgsConstructor
public class AtualizarStatusReservaUserCase {

    private final BuscarReservaPorIdGateway buscarReservaPorIdGateway;
    private final AtualizarStatusReservaGateway atualizarStatusReservaGateway;
    private final AtualizarStatusReservaPresenter atualizarStatusReservaPresenter;

    public AtualizarReservaOutput atualizarStatus(String id, ReservaStatus status) {

        if (!status.equals(ReservaStatus.CANCELADO) && !status.equals(ReservaStatus.CONCLUIDO)) {
            throw new ExceptionAdvice(CodigoError.STATUS_RESERVA_INVALIDO);
        }

        var reserva = buscarReservaPorIdGateway.buscarPorId(id).orElseThrow(
                () -> new ExceptionAdvice(RESERVA_NAO_ENCONTRADA));

        reserva.setStatus(status);

        reserva = atualizarStatusReservaGateway.atualizarStatus(reserva);

        return atualizarStatusReservaPresenter.reservaEmAtualizarReservaOutput(reserva);
    }
}
