package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.ListarTodosRestauranteUserCase;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.ListarTodosRestauranteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestauranteResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageTodosRestaurantesOutput;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarTodosRestauranteController.class)
class ListarTodosRestauranteControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarTodosRestauranteUserCaseFactory listarTodosRestauranteUserCaseFactory;

    @MockBean
    private ListarTodosRestauranteUserCase listarTodosRestauranteUserCase;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private RestaurantePresenter restaurantePresenter;

    @MockBean
    private ListarTodosRestaurantePresenter listarTodosRestaurantePresenter;

    @Test
    void testeListarTodos() throws Exception {

        when(listarTodosRestauranteUserCaseFactory.buildListarTodosRestauranteUserCase(
                listarTodosRestaurantePresenter, restaurantePresenter, restauranteRepository)).thenReturn(listarTodosRestauranteUserCase);
        when(listarTodosRestauranteUserCase.listarTodos(0, 10)).thenReturn(getPageTodosRestaurantesOutput());
        when(listarTodosRestaurantePresenter.pageTodosRestaurantesOutputEmPageRestauranteResponse(getPageTodosRestaurantesOutput()))
                .thenReturn(getPageRestauranteResponse());

        mockMvc.perform(get("/restaurantes")
                .param("pagina","0")
                .param("tamanho", "10"))
                .andExpect(status().isOk());

    }

    @Test
    void testeListarTodosSemTamanho() throws Exception {

        when(listarTodosRestauranteUserCaseFactory.buildListarTodosRestauranteUserCase(
                listarTodosRestaurantePresenter, restaurantePresenter, restauranteRepository)).thenReturn(listarTodosRestauranteUserCase);
        when(listarTodosRestauranteUserCase.listarTodos(0, 50)).thenReturn(getPageTodosRestaurantesOutput());
        when(listarTodosRestaurantePresenter.pageTodosRestaurantesOutputEmPageRestauranteResponse(getPageTodosRestaurantesOutput()))
                .thenReturn(getPageRestauranteResponse());

        mockMvc.perform(get("/restaurantes")
                        .param("pagina","0"))
                .andExpect(status().isOk());
    }

    @Test
    void testeListarTodosSemPagina() throws Exception {

        when(listarTodosRestauranteUserCaseFactory.buildListarTodosRestauranteUserCase(
                listarTodosRestaurantePresenter, restaurantePresenter, restauranteRepository)).thenReturn(listarTodosRestauranteUserCase);
        when(listarTodosRestauranteUserCase.listarTodos(0, 10)).thenReturn(getPageTodosRestaurantesOutput());
        when(listarTodosRestaurantePresenter.pageTodosRestaurantesOutputEmPageRestauranteResponse(getPageTodosRestaurantesOutput()))
                .thenReturn(getPageRestauranteResponse());

        mockMvc.perform(get("/restaurantes")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());
    }

}