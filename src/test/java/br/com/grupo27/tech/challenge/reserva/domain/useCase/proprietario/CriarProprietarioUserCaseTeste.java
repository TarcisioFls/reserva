package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.CriarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.CriarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_JA_CADASTRADO;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarProprietarioUserCaseTeste extends TesteConfig {

    @InjectMocks
    private CriarProprietarioUserCase criarProprietarioUserCase;

    @Mock
    private CriarProprietarioGateway criarProprietarioGateway;

    @Mock
    private CriarProprietarioPresenter criarProprietarioPresenter;


    @Test
    void testeCriarProprietarioComSucesso() {
        var criarProprietarioInput = getCriarProprietarioInput();
        var proprietario = getProprietario();
        var criarProprietarioOutput = getCriarProprietarioOutput();

        when(criarProprietarioGateway.buscaPorEmail(any(String.class))).thenReturn(Optional.empty());
        when(criarProprietarioPresenter.criarProprietarioInputEmProprietario(any(CriarProprietarioInput.class))).thenReturn(proprietario);
        when(criarProprietarioGateway.criar(any(Proprietario.class))).thenReturn(proprietario);
        when(criarProprietarioPresenter.proprietarioEmCriarProprietarioOutput(any(Proprietario.class))).thenReturn(criarProprietarioOutput);

        var result = criarProprietarioUserCase.criar(criarProprietarioInput);

        assertEquals(criarProprietarioOutput, result);

        verify(criarProprietarioGateway, times(1)).buscaPorEmail(any(String.class));
        verify(criarProprietarioPresenter, times(1)).criarProprietarioInputEmProprietario(any(CriarProprietarioInput.class));
        verify(criarProprietarioGateway, times(1)).criar(any(Proprietario.class));
        verify(criarProprietarioPresenter, times(1)).proprietarioEmCriarProprietarioOutput(any(Proprietario.class));
    }

    @Test
    void testeCriarProprietarioComEmailJaCadastrado() {
        var criarProprietarioInput = getCriarProprietarioInput();
        var proprietario = getProprietario();

        when(criarProprietarioGateway.buscaPorEmail(criarProprietarioInput.getEmail())).thenReturn(Optional.of(proprietario));

        var exception = assertThrows(ExceptionAdvice.class, () -> criarProprietarioUserCase.criar(criarProprietarioInput));

        assertEquals(EMAIL_JA_CADASTRADO, exception.getCodigoError());

        verify(criarProprietarioGateway, times(1)).buscaPorEmail(any(String.class));
        verify(criarProprietarioPresenter, times(0)).criarProprietarioInputEmProprietario(any(CriarProprietarioInput.class));
        verify(criarProprietarioGateway, times(0)).criar(any(Proprietario.class));
        verify(criarProprietarioPresenter, times(0)).proprietarioEmCriarProprietarioOutput(any(Proprietario.class));
    }

}