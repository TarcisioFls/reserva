package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarAvalicaoPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.getPageAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.getPageListarAvaliacaoPorRestauranteIdOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ListarAvaliacaoPorRestauranteIdUserCaseTest extends TesteConfig {

    @Mock
    private ListarAvalicaoPorRestauranteIdGateway listarAvalicaoPorRestauranteIdGateway;

    @Mock
    private ListarAvaliacaoPorRestauranteIdPresenter listarAvaliacaoPorRestauranteIdPresenter;

    @InjectMocks
    private ListarAvaliacaoPorRestauranteIdUserCase listarAvaliacaoPorRestauranteIdUserCase;

    @Test
    void TestaListarAvaliacaoPorRestauranteComSucesso(){

        int pagina = 0;
        int tamanho = 10;
        var restauranteId = "66c67aa035ed1f735450b7a2";

        var pageListarAvalicaoPorRestauranteId = getPageAvaliacao();
        var pageListarAvalicaoPorRestauranteIdOutput = getPageListarAvaliacaoPorRestauranteIdOutput();

        when(listarAvalicaoPorRestauranteIdGateway.listarPorRestaurante(restauranteId, pagina, tamanho)).thenReturn(pageListarAvalicaoPorRestauranteId);
        when(listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(pageListarAvalicaoPorRestauranteId))
                .thenReturn(pageListarAvalicaoPorRestauranteIdOutput);

        var resultado = listarAvaliacaoPorRestauranteIdUserCase.listarPorRestauranteId(restauranteId, pagina, tamanho);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), pageListarAvalicaoPorRestauranteIdOutput.getContent().getFirst());
        verify(listarAvalicaoPorRestauranteIdGateway, times(1)).listarPorRestaurante(restauranteId, pagina, tamanho);
        verify(listarAvaliacaoPorRestauranteIdPresenter, times(1)).pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(pageListarAvalicaoPorRestauranteId);




    }

}
