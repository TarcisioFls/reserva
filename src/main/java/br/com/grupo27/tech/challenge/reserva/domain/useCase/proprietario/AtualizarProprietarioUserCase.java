package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.AtualizarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AtualizarProprietarioUserCase {

    private final AtualizarProprietarioGateway atualizarProprietarioGateway;
    private final AtualizarProprietarioPresenter atualizarProprietarioPresenter;

    public AtualizarProprietarioOutput atualizar(AtualizarProprietarioInput atualizarProprietarioInput) {

        var proprietario = atualizarProprietarioGateway.buscarPorId(atualizarProprietarioInput.getId()).orElseThrow(
                () -> new ExceptionAdvice(CodigoError.PROPRIETARIO_NAO_ENCONTRADO)
        );

        if (atualizarProprietarioGateway.buscarPorEmail(atualizarProprietarioInput.getEmail()).isPresent()) {
            throw new ExceptionAdvice(CodigoError.EMAIL_JA_CADASTRADO);
        }

        proprietario = atualizarProprietarioPresenter.atualizarProprietarioInputEmProprietario(proprietario, atualizarProprietarioInput);
        proprietario = atualizarProprietarioGateway.atualizar(proprietario);

        return atualizarProprietarioPresenter.proprietarioEmAtualizarProprietarioOutput(proprietario);
    }

}
