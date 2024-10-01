package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.ListarAvaliacaoPorRestauranteIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.ListarAvaliacaoPorRestauranteIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.getPageListarAvaliacaoPorRestauranteIdOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.getPageListarAvaliacaoPorRestauranteResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarAvaliacaoPorRestauranteIdController.class)
class ListarAvaliacaoPorRestauranteIdControllerTest extends TesteConfig {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarAvaliacaoPorRestauranteIdUserCaseFactory factory;

    @MockBean
    private ListarAvaliacaoPorRestauranteIdUserCase listarAvaliacaoPorRestauranteIdUserCase;

    @MockBean
    private ListarAvaliacaoPorRestauranteIdPresenter listarAvaliacaoPorRestauranteIdPresenter;

    @MockBean
    private AvaliacaoPresenter avaliacaoPresenter;

    @MockBean
    private AvaliacaoRepository avaliacaoRepository;

    @MockBean
    private ReservaPresenter reservaPresenter;

    @MockBean
    private ReservaRepository reservaRepository;

    private final int pagina = 0;
    private final int tamanho = 10;

    @Test
    void testaListarAvaliacaoPorRestauranteId() throws Exception{

        var restauranteId = "66c67aa035ed1f735450b7a2";

        when(factory.buildListarAvaliacaoPorRestauranteIdUserCase(listarAvaliacaoPorRestauranteIdPresenter,
                avaliacaoPresenter,
                avaliacaoRepository,
                reservaPresenter,
                reservaRepository))
                .thenReturn(listarAvaliacaoPorRestauranteIdUserCase);

        when(listarAvaliacaoPorRestauranteIdUserCase.listarPorRestauranteId(restauranteId, pagina, tamanho))
                .thenReturn(getPageListarAvaliacaoPorRestauranteIdOutput());

        when(listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(getPageListarAvaliacaoPorRestauranteIdOutput()))
                .thenReturn(getPageListarAvaliacaoPorRestauranteResponse());

        mockMvc.perform(get("/avaliacoes/restaurante/{restauranteId}", restauranteId)
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testaListarAvaliacaoPorRestauranteIdSemTamanho() throws Exception{

        var restauranteId = "66c67aa035ed1f735450b7a2";

        when(factory.buildListarAvaliacaoPorRestauranteIdUserCase(listarAvaliacaoPorRestauranteIdPresenter,
                avaliacaoPresenter,
                avaliacaoRepository,
                reservaPresenter,
                reservaRepository))
                .thenReturn(listarAvaliacaoPorRestauranteIdUserCase);

        when(listarAvaliacaoPorRestauranteIdUserCase.listarPorRestauranteId(restauranteId, pagina, tamanho))
                .thenReturn(getPageListarAvaliacaoPorRestauranteIdOutput());

        when(listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(getPageListarAvaliacaoPorRestauranteIdOutput()))
                .thenReturn(getPageListarAvaliacaoPorRestauranteResponse());

        mockMvc.perform(get("/avaliacoes/restaurante/{restauranteId}", restauranteId)
                        .param("pagina", "0"))
                .andExpect(status().isOk());
    }




}
