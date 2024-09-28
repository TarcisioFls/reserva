package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.AtualizarProprietarioGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.mock.proprietario.AtualizarProprietarioDados;
import br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.AtualizarProprietarioDados.getAtualizarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.AtualizarProprietarioDados.getAtualizarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioAtualizado;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarProprietarioUserCaseTest extends TesteConfig {

    @InjectMocks
    private AtualizarProprietarioUserCase atualizarProprietarioUserCase;

    @Mock
    private AtualizarProprietarioGateway atualizarProprietarioGateway;

    @Mock
    private AtualizarProprietarioPresenter atualizarProprietarioPresenter;

    @Test
    void testeAtualizarProprietarioComSucesso() {

        var proprietario = getProprietario();
        var atualizarProprietarioInput = getAtualizarProprietarioInput();
        var proprietarioAtualizado = getProprietarioAtualizado();

        when(atualizarProprietarioGateway.buscarPorId(atualizarProprietarioInput.getId())).thenReturn(Optional.of(proprietario));
        when(atualizarProprietarioGateway.buscarPorEmail(anyString())).thenReturn(Optional.empty());
        when(atualizarProprietarioPresenter.atualizarProprietarioInputEmProprietario(proprietario, atualizarProprietarioInput)).thenReturn(proprietarioAtualizado);
        when(atualizarProprietarioGateway.atualizar(any(Proprietario.class))).thenReturn(proprietarioAtualizado);
        when(atualizarProprietarioPresenter.proprietarioEmAtualizarProprietarioOutput(any(Proprietario.class))).thenReturn(getAtualizarProprietarioOutput());

        atualizarProprietarioUserCase.atualizar(atualizarProprietarioInput);

        verify(atualizarProprietarioGateway, times(1)).buscarPorId(atualizarProprietarioInput.getId());
        verify(atualizarProprietarioGateway, times(1)).buscarPorEmail(anyString());
        verify(atualizarProprietarioPresenter, times(1)).atualizarProprietarioInputEmProprietario(proprietario, atualizarProprietarioInput);
        verify(atualizarProprietarioGateway, times(1)).atualizar(any(Proprietario.class));
        verify(atualizarProprietarioPresenter, times(1)).proprietarioEmAtualizarProprietarioOutput(any(Proprietario.class));

    }

    @Test
    void testeAtualizarProprietarioComProprietarioNaoEncontrado() {

        var atualizarProprietarioInput = AtualizarProprietarioDados.getAtualizarProprietarioInput();

        when(atualizarProprietarioGateway.buscarPorId(atualizarProprietarioInput.getId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> atualizarProprietarioUserCase.atualizar(atualizarProprietarioInput));

        verify(atualizarProprietarioGateway, times(1)).buscarPorId(atualizarProprietarioInput.getId());
        verify(atualizarProprietarioGateway, never()).buscarPorEmail(anyString());
        verify(atualizarProprietarioPresenter, never()).atualizarProprietarioInputEmProprietario(any(Proprietario.class), any(AtualizarProprietarioInput.class));
        verify(atualizarProprietarioGateway, never()).atualizar(any(Proprietario.class));
        verify(atualizarProprietarioPresenter, never()).proprietarioEmAtualizarProprietarioOutput(any(Proprietario.class));
    }

    @Test
    void testeAtualizarProprietarioComEmailJaCadastrado() {

        var atualizarProprietarioInput = AtualizarProprietarioDados.getAtualizarProprietarioInput();
        var proprietario = ProprietarioDados.getProprietario();

        when(atualizarProprietarioGateway.buscarPorId(atualizarProprietarioInput.getId())).thenReturn(Optional.of(proprietario));
        when(atualizarProprietarioGateway.buscarPorEmail(anyString())).thenReturn(Optional.of(proprietario));

        assertThrows(Exception.class, () -> atualizarProprietarioUserCase.atualizar(atualizarProprietarioInput));

        verify(atualizarProprietarioGateway, times(1)).buscarPorId(atualizarProprietarioInput.getId());
        verify(atualizarProprietarioGateway, times(1)).buscarPorEmail(anyString());
        verify(atualizarProprietarioPresenter, never()).atualizarProprietarioInputEmProprietario(proprietario, atualizarProprietarioInput);
        verify(atualizarProprietarioGateway, never()).atualizar(any(Proprietario.class));
        verify(atualizarProprietarioPresenter, never()).proprietarioEmAtualizarProprietarioOutput(any(Proprietario.class));
    }

}
