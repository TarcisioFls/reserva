package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.ListarTodosProprietariosGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageTodosProprietariosOutput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarTodosProprietariosUserCaseTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosProprietariosUserCase listarTodosProprietariosUserCase;

    @Mock
    private ListarTodosProprietariosGateway listarTodosProprietariosGateway;

    @Mock
    private ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;

    @Test
    void testListarTodos() {
        int pagina = 0;
        int tamanho = 10;

        when(listarTodosProprietariosGateway.listarTodos(PageRequest.of(pagina, tamanho))).thenReturn(getPageProprietario());
        when(listarTodosProprietariosPresenter.pageProprietarioEmPageTodosProprietariosOutput(getPageProprietario())).thenReturn(getPageTodosProprietariosOutput());

        var resultado = listarTodosProprietariosUserCase.listarTodos(pagina, tamanho);

        assertEquals(getPageTodosProprietariosOutput(), resultado);

        verify(listarTodosProprietariosGateway, times(1)).listarTodos(PageRequest.of(pagina, tamanho));
        verify(listarTodosProprietariosPresenter, times(1)).pageProprietarioEmPageTodosProprietariosOutput(getPageProprietario());
    }

}