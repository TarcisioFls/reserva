package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.CriarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.CriarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.CriarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CPF_JA_CADASTRADO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_JA_CADASTRADO;

@Getter
@RequiredArgsConstructor
public class CriarProprietarioUserCase {

    private final CriarProprietarioGateway criarProprietarioGateway;
    private final CriarProprietarioPresenter criarProprietarioPresenter;

    public CriarProprietarioOutput criar(CriarProprietarioInput criarProprietarioInput) {
        if (criarProprietarioGateway.buscaPorEmail(criarProprietarioInput.getEmail()).isPresent()) {
            throw new ExceptionAdvice(EMAIL_JA_CADASTRADO);
        }

        if (criarProprietarioGateway.buscaPorCpf(criarProprietarioInput.getCpf()).isPresent()) {
            throw new ExceptionAdvice(CPF_JA_CADASTRADO);
        }

        var proprietario = criarProprietarioPresenter.criarProprietarioInputEmProprietario(criarProprietarioInput);
        proprietario = criarProprietarioGateway.criar(proprietario);

        return criarProprietarioPresenter.proprietarioEmCriarProprietarioOutput(proprietario);
    }

}
