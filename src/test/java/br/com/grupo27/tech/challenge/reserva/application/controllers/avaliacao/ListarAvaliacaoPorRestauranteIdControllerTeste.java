package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.ListarAvaliacaoPorRestauranteIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenterImpl;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.ListarAvaliacaoPorRestauranteIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModel;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.getPageListarAvaliacaoPorRestauranteIdOutput;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarAvaliacaoPorRestauranteIdController.class)
public class ListarAvaliacaoPorRestauranteIdControllerTeste extends TesteConfig {


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

        mockMvc.perform(get("/avaliacoes/restauranteid/{id}", restauranteId)
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

        mockMvc.perform(get("/avaliacoes/restauranteid/{id}", restauranteId)
                        .param("pagina", "0"))
                .andExpect(status().isOk());
    }




}
