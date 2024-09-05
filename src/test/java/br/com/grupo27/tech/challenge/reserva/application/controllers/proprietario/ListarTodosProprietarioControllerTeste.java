package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ListarTodosProprietariosUserCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageProprietariosResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageTodosProprietariosOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ListarTodosProprietarioControllerTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosProprietarioController listarTodosProprietarioController;

    @Mock
    private ListarTodosProprietariosUserCase listarTodosProprietariosUserCase;

    @Mock
    private ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;


    void testListarTodos() {
        int pagina = 0;
        int tamanho = 10;

        when(listarTodosProprietariosUserCase.listarTodos(pagina, tamanho)).thenReturn(getPageTodosProprietariosOutput());
        when(listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(getPageTodosProprietariosOutput())).thenReturn(getPageProprietariosResponse());

        var resultado = listarTodosProprietarioController.listarTodos(pagina, tamanho);

        assertEquals(getPageProprietariosResponse(), resultado.getBody());
    }

}