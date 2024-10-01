package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.DeletaProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantesPorProprietarioIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletaProprietarioPorIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private DeletaProprietarioPorIdUserCase deletaProprietarioPorIdUserCase;

    @Mock
    private DeletaProprietarioPorIdGateway deletaProprietarioPorIdGateway;

    @Mock
    private BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;

    @Mock
    private DeletaRestaurantesPorProprietarioIdGateway deletaRestaurantesPorProprietarioIdGateway;

    @Test
    void testeDeletePorId() {
        var id = "66c67aa035ed1f735450b7a2";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getProprietario()));
        doNothing().when(deletaProprietarioPorIdGateway).deletaPorId(id);
        doNothing().when(deletaRestaurantesPorProprietarioIdGateway).deletaPorProprietarioId(id);

        deletaProprietarioPorIdUserCase.deletaPorId(id);

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(deletaProprietarioPorIdGateway, times(1)).deletaPorId(id);
        verify(deletaRestaurantesPorProprietarioIdGateway, times(1)).deletaPorProprietarioId(id);
    }

    @Test
    void testeDeletePorIdProprietarioNaoEncontrado() {
        var id = "IdInexistente";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(empty());

        var resultado = assertThrows(ExceptionAdvice.class, () -> deletaProprietarioPorIdUserCase.deletaPorId(id));

        assertEquals("Proprietário não encontrado", resultado.getMessage());

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(deletaProprietarioPorIdGateway, never()).deletaPorId(id);
        verify(deletaRestaurantesPorProprietarioIdGateway, never()).deletaPorProprietarioId(id);
    }

}