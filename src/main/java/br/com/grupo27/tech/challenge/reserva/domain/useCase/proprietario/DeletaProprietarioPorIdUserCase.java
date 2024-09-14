package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.DeletaProprietarioPorIdGateway;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class DeletaProprietarioPorIdUserCase {

    private final DeletaProprietarioPorIdGateway deletaProprietarioPorIdGateway;
    private final BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;

    public void deletaPorId(String id) {

        if (buscarProprietarioPorIdGateway.buscarPorId(id).isEmpty()) {
            throw new ExceptionAdvice(PROPRIETARIO_NAO_ENCONTRADO);
        }

        //TODO: Implementar regra para verificar se existe Restaurante vinculado ao proprietário?

        deletaProprietarioPorIdGateway.deletaPorId(id);
    }

}
