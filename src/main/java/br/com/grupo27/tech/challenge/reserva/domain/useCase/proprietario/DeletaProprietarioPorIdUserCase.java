package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.DeletaProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantesPorProprietarioIdGateway;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class DeletaProprietarioPorIdUserCase {

    private final DeletaProprietarioPorIdGateway deletaProprietarioPorIdGateway;
    private final BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;
    private final DeletaRestaurantesPorProprietarioIdGateway deletaRestaurantesPorProprietarioIdGateway;

    public void deletaPorId(String id) {

        if (buscarProprietarioPorIdGateway.buscarPorId(id).isEmpty()) {
            throw new ExceptionAdvice(PROPRIETARIO_NAO_ENCONTRADO);
        }

        deletaProprietarioPorIdGateway.deletaPorId(id);
        deletaRestaurantesPorProprietarioIdGateway.deletaPorProprietarioId(id);
    }

}
