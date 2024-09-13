package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.ListarTodosRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageTodosRestaurantesOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarTodosRestauranteUserCaseTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosRestauranteUserCase listarTodosRestauranteUserCase;

    @Mock
    private ListarTodosRestauranteGateway listarTodosRestauranteGateway;

    @Mock
    private ListarTodosRestaurantePresenter listarTodosRestaurantePresenter;

    @Test
    void testeListarTodos() {

        var pagina = 0;
        var tamanho = 10;
        var paginaRequest = PageRequest.of(pagina, tamanho);
        var pageRestaurante = getPageRestaurante();

        when(listarTodosRestauranteGateway.listarTodos(paginaRequest)).thenReturn(pageRestaurante);
        when(listarTodosRestaurantePresenter.pageRestauranteEmPageTodosRestauranteOutput(pageRestaurante)).thenReturn(getPageTodosRestaurantesOutput());

        var resultado = listarTodosRestauranteUserCase.listarTodos(pagina, tamanho);

        assertEquals(getPageTodosRestaurantesOutput(), resultado);

        verify(listarTodosRestauranteGateway, times(1)).listarTodos(paginaRequest);
        verify(listarTodosRestaurantePresenter, times(1)).pageRestauranteEmPageTodosRestauranteOutput(pageRestaurante);

    }
}