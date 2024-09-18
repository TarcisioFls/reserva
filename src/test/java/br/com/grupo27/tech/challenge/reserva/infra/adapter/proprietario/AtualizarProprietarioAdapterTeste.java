package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioAtualizado;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarProprietarioAdapterTeste extends TesteConfig {

    @InjectMocks
    private AtualizarProprietarioAdapter atualizarProprietarioAdapter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private ProprietarioPresenter proprietarioPresenter;

    @Test
    void testaAtualizarComSucesso() {

        var proprietarioModel = ProprietarioDados.getProprietarioModelAtualizado();

        when(proprietarioPresenter.proprietarioEmProprietarioModel(any(Proprietario.class))).thenReturn(proprietarioModel);
        when(proprietarioRepository.save(any(ProprietarioModel.class))).thenReturn(proprietarioModel);
        when(proprietarioPresenter.proprietarioModelEmProprietario(any(ProprietarioModel.class))).thenReturn(getProprietarioAtualizado());

        atualizarProprietarioAdapter.atualizar(getProprietarioAtualizado());

        verify(proprietarioPresenter, times(1)).proprietarioEmProprietarioModel(any(Proprietario.class));
        verify(proprietarioRepository, times(1)).save(any(ProprietarioModel.class));
        verify(proprietarioPresenter, times(1)).proprietarioModelEmProprietario(any(ProprietarioModel.class));

    }

    @Test
    void testaBuscarPorIdComSucesso() {

        var proprietarioModel = ProprietarioDados.getProprietarioModelAtualizado();
        var proprietario = getProprietarioAtualizado();

        when(proprietarioRepository.findById(any(String.class))).thenReturn(Optional.of(proprietarioModel));
        when(proprietarioPresenter.proprietarioModelEmProprietario(any(ProprietarioModel.class))).thenReturn(proprietario);

        atualizarProprietarioAdapter.buscarPorId("66c67aa035ed1f735450b7a2");

        verify(proprietarioRepository, times(1)).findById(any(String.class));
        verify(proprietarioPresenter, times(1)).proprietarioModelEmProprietario(any(ProprietarioModel.class));

    }

    @Test
    void testaBuscarPorEmailComSucesso() {

        var proprietarioModel = ProprietarioDados.getProprietarioModelAtualizado();
        var proprietario = getProprietarioAtualizado();

        when(proprietarioRepository.findByEmail(any(String.class))).thenReturn(Optional.of(proprietarioModel));
        when(proprietarioPresenter.proprietarioModelEmProprietario(any(ProprietarioModel.class))).thenReturn(proprietario);

        atualizarProprietarioAdapter.buscarPorEmail("maria@teste.com");

        verify(proprietarioRepository, times(1)).findByEmail(any(String.class));
        verify(proprietarioPresenter, times(1)).proprietarioModelEmProprietario(any(ProprietarioModel.class));

    }

}