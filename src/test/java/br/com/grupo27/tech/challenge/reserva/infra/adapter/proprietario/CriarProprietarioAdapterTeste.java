package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenterImpl;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarProprietarioAdapterTeste extends TesteConfig {

    @InjectMocks
    private CriarProprietarioAdapter criarProprietarioAdapter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private ProprietarioPresenterImpl proprietarioPresenter;

    @Test
    void testarCriar() {
        var proprietarioModel = getProprietarioModel();
        var proprietario = getProprietario();
        var proprietarioEsperado = getProprietarioDepoisDeSalvar();

        when(proprietarioPresenter.proprietarioEmProprietarioModel(any(Proprietario.class))).thenReturn(proprietarioModel);
        when(proprietarioRepository.save(any(ProprietarioModel.class))).thenReturn(proprietarioModel);
        when(proprietarioPresenter.proprietarioModelEmProprietario(any(ProprietarioModel.class))).thenReturn(proprietarioEsperado);

        var resultado = criarProprietarioAdapter.criar(proprietario);

        assertEquals(proprietarioEsperado, resultado);

        verify(proprietarioPresenter, times(1)).proprietarioEmProprietarioModel(any(Proprietario.class));
        verify(proprietarioRepository, times(1)).save(any(ProprietarioModel.class));
        verify(proprietarioPresenter, times(1)).proprietarioModelEmProprietario(any(ProprietarioModel.class));
    }

    @Test
    void testaBuscaPorEmailExistente() {
        var email = "joao@teste.com";
        var proprietarioModel = getProprietarioModel();
        var proprietario = getProprietario();

        when(proprietarioRepository.findByEmail(email)).thenReturn(Optional.of(proprietarioModel));
        when(proprietarioPresenter.proprietarioModelEmProprietario(any(ProprietarioModel.class))).thenReturn(proprietario);

        var resultado = criarProprietarioAdapter.buscaPorEmail(email);

        assertEquals(Optional.of(proprietario), resultado);
    }

    @Test
    void testaBuscaPorEmailRetornaVazioQuandoEmailNaoExiste() {
        var email = "teste@teste.com";

        when(proprietarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        var resultado = criarProprietarioAdapter.buscaPorEmail(email);

        assertEquals(Optional.empty(), resultado);

    }

    private static Proprietario getProprietarioDepoisDeSalvar() {

        return getProprietario();
    }

}