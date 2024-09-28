package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletaProprietarioPorIdAdapterTest extends TesteConfig {

    @InjectMocks
    private DeletaProprietarioPorIdAdapter deletaProprietarioPorIdAdapter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Test
    void deletaPorIdSuccessfullyDeletesProprietario() {
        String id = "66c67aa035ed1f735450b7a2";

        deletaProprietarioPorIdAdapter.deletaPorId(id);

        verify(proprietarioRepository, times(1)).deleteById(id);
    }

}