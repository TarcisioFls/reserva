package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.BuscarProprietarioPorIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.BuscarProprietarioPorIdPresenter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class BuscarProprietarioPorIdUserCase {

    private final BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;
    private final BuscarProprietarioPorIdPresenter buscarProprietarioPorIdPresenter;

    public BuscarProprietarioPorIdOutput buscarPorId(String id) {

        var proprietario = buscarProprietarioPorIdGateway.buscarPorId(id).orElseThrow(
                () -> new ExceptionAdvice(PROPRIETARIO_NAO_ENCONTRADO)
        );

        return buscarProprietarioPorIdPresenter.proprietarioEmBuscarProprietarioPorIdOutput(proprietario);
    }
}
