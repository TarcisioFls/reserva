package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.buscarRestaurantesResponseList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaController.class)
class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory buscarRestaurantesUserCaseFactory;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private RestaurantePresenter restaurantePresenter;

    @MockBean
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter buscarRestaurantesPresenter;

    @MockBean
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase buscarRestaurantesUserCase;

    @Test
    void testeBuscarRestaurantes() throws Exception {

        var buscar = "Aki";
        var buscarRestauranteOutputList = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput();

        when(buscarRestaurantesUserCaseFactory.buildBuscarRestauranteUserCase(
                restauranteRepository,
                restaurantePresenter,
                buscarRestaurantesPresenter)
        ).thenReturn(buscarRestaurantesUserCase);
        when(buscarRestaurantesUserCase.buscarRestaurantes(buscar))
                .thenReturn(buscarRestauranteOutputList);
        when(buscarRestaurantesPresenter
                .buscarRestaurantesOutputEmRestaurantesResponse(buscarRestauranteOutputList))
                .thenReturn(buscarRestaurantesResponseList());

        mockMvc.perform(get("/restaurantes/{buscar}", buscar))
                .andExpect(status().isOk());

        verify(buscarRestaurantesUserCaseFactory, times(1))
                .buildBuscarRestauranteUserCase(restauranteRepository, restaurantePresenter, buscarRestaurantesPresenter);
        verify(buscarRestaurantesUserCase, times(1)).buscarRestaurantes(buscar);
        verify(buscarRestaurantesPresenter, times(1))
                .buscarRestaurantesOutputEmRestaurantesResponse(buscarRestauranteOutputList);

    }

    @Test
    void testeBuscarRestaurantesSemResultado() throws Exception {

        var buscar = "Reteteu";

        when(buscarRestaurantesUserCaseFactory.buildBuscarRestauranteUserCase(
                restauranteRepository,
                restaurantePresenter,
                buscarRestaurantesPresenter)
        ).thenReturn(buscarRestaurantesUserCase);
        when(buscarRestaurantesUserCase.buscarRestaurantes(buscar))
                .thenReturn(new PagedModel<>(Page.empty()));
        when(buscarRestaurantesPresenter
                .buscarRestaurantesOutputEmRestaurantesResponse(new PagedModel<>(Page.empty())))
                .thenReturn(new PagedModel<>(Page.empty()));

        mockMvc.perform(get("/restaurantes/{buscar}", buscar))
                .andExpect(status().isOk());

        verify(buscarRestaurantesUserCaseFactory, times(1))
                .buildBuscarRestauranteUserCase(restauranteRepository, restaurantePresenter, buscarRestaurantesPresenter);
        verify(buscarRestaurantesUserCase, times(1)).buscarRestaurantes(buscar);
        verify(buscarRestaurantesPresenter, times(1))
                .buscarRestaurantesOutputEmRestaurantesResponse(new PagedModel<>(Page.empty()));

    }


}