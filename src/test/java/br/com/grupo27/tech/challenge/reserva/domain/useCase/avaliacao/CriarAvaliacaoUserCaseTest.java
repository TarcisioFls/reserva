package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.CriarAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoInput;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados.getReserva;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarAvaliacaoUserCaseTest extends TesteConfig {

    @Mock
    private BuscarReservaPorIdGateway buscarReservaPorIdGateway;

    @Mock
    private CriarAvaliacaoGateway criarAvaliacaoGateway;

    @Mock
    private CriarAvaliacaoPresenter criarAvaliacaoPresenter;

    @InjectMocks
    private CriarAvaliacaoUserCase criarAvaliacaoUserCase;

    @Test
    void testeCriarAvaliacaoComSucesso() {

        var criarAvaliacaoInput = getCriarAvaliacaoInput();
        var avaliacao = getCriarAvaliacao();
        var criarAvaliacaoOutput = getCriarAvaliacaoOutput();

        when(criarAvaliacaoPresenter.criarAvaliacaoInputEmAvaliacao(criarAvaliacaoInput)).thenReturn(avaliacao);
        when(buscarReservaPorIdGateway.buscarPorId(criarAvaliacaoInput.getReservaId())).thenReturn(Optional.of(getReserva()));
        when(criarAvaliacaoGateway.criar(avaliacao)).thenReturn(avaliacao);
        when(criarAvaliacaoPresenter.avaliacaoEmCriarAvaliacaoOutput(avaliacao)).thenReturn(criarAvaliacaoOutput);

        var resultado = criarAvaliacaoUserCase.criar(criarAvaliacaoInput);

        assertEquals(criarAvaliacaoOutput, resultado);

        verify(criarAvaliacaoPresenter, times(1)).criarAvaliacaoInputEmAvaliacao(criarAvaliacaoInput);
        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(criarAvaliacaoInput.getReservaId());
        verify(criarAvaliacaoGateway, times(1)).criar(avaliacao);
        verify(criarAvaliacaoPresenter, times(1)).avaliacaoEmCriarAvaliacaoOutput(avaliacao);
    }
}