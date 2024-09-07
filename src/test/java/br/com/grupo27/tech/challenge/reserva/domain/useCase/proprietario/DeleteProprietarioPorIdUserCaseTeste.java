package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.DeletaProprietarioPorIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietario;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteProprietarioPorIdUserCaseTeste extends TesteConfig {

    @InjectMocks
    private DeleteProprietarioPorIdUserCase deleteProprietarioPorIdUserCase;

    @Mock
    private DeletaProprietarioPorIdGateway deletaProprietarioPorIdGateway;

    @Mock
    private BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;

    @Test
    void testeDeletePorId() {
        var id = "66c67aa035ed1f735450b7a2";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getProprietario()));

        deleteProprietarioPorIdUserCase.deletaPorId(id);

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(deletaProprietarioPorIdGateway, times(1)).deletaPorId(id);
    }

    @Test
    void testeDeletePorIdProprietarioNaoEncontrado() {
        var id = "IdInexistente";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(empty());

        var resultado = assertThrows(ExceptionAdvice.class, () -> deleteProprietarioPorIdUserCase.deletaPorId(id));

        assertEquals("Proprietário não encontrado", resultado.getMessage());

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(deletaProprietarioPorIdGateway, times(0)).deletaPorId(id);
    }

}