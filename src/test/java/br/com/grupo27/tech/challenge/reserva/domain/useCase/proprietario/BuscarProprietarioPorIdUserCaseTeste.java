package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.BuscarProprietarioPorIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.BuscarProprietarioPorIdDados.buscarProprietarioPorIdOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarProprietarioPorIdUserCaseTeste extends TesteConfig {

    @InjectMocks
    private BuscarProprietarioPorIdUserCase buscarProprietarioPorIdUserCase;

    @Mock
    private BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;

    @Mock
    private BuscarProprietarioPorIdPresenter buscarProprietarioPorIdPresenter;

    @Test
    void testaBuscarProprietarioPorId() {

        var id = "66c67aa035ed1f735450b7a2";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getProprietario()));
        when(buscarProprietarioPorIdPresenter.proprietarioEmBuscarProprietarioPorIdOutput(getProprietario()))
                .thenReturn(buscarProprietarioPorIdOutput());

        var resultado = buscarProprietarioPorIdUserCase.buscarPorId(id);

        assertAll("Validando o retorno da busca por id",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getCpf()),
                () -> assertEquals("11999999999", resultado.getTelefone())
        );

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(buscarProprietarioPorIdPresenter, times(1)).proprietarioEmBuscarProprietarioPorIdOutput(getProprietario());

    }

    @Test
    void testaBuscarProprietarioPorIdNaoEncontrado() {

        var id = "66c67aa035";

        when(buscarProprietarioPorIdGateway.buscarPorId(id)).thenReturn(Optional.empty());

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> buscarProprietarioPorIdUserCase.buscarPorId(id));

        assertEquals("Proprietário não encontrado", exceptionAdvice.getMessage());

        verify(buscarProprietarioPorIdGateway, times(1)).buscarPorId(id);
        verify(buscarProprietarioPorIdPresenter, never()).proprietarioEmBuscarProprietarioPorIdOutput(Mockito.any());
    }



}