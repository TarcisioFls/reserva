package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BuscarProprietarioPorIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private BuscarProprietarioPorIdAdapter buscarProprietarioPorIdAdapter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private ProprietarioPresenter proprietarioPresenter;

    @Test
    void testebBuscarPorId() {

        var id = "66c67aa035ed1f735450b7a2";

        when(proprietarioRepository.findById(id)).thenReturn(Optional.of(getProprietarioModel()));
        when(proprietarioPresenter.proprietarioModelEmProprietario(getProprietarioModel())).thenReturn(getProprietario());

        var result = buscarProprietarioPorIdAdapter.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(getProprietario(), result.get());

    }
}