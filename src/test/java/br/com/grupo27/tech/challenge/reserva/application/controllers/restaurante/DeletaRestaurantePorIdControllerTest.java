package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.DeletaRestaurantePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.DeletaRestaurantePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeletaRestaurantePorIdController.class)
class DeletaRestaurantePorIdControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeletaRestaurantePorIdUserCaseFactory deletaRestaurantePorIdUserCaseFactory;

    @MockBean
    private DeletaRestaurantePorIdUserCase deletaRestaurantePorIdUserCase;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private RestaurantePresenter restaurantePresenter;

    @MockBean
    private ReservaRepository reservaRepository;

    @Test
    void testeDeletePorId() throws Exception {

        var id = "44c67aa035ed1f735450b72a";

        when(deletaRestaurantePorIdUserCaseFactory.buildDeletaRestaurantePorIdUserCase(
                restauranteRepository, restaurantePresenter, reservaRepository))
                .thenReturn(deletaRestaurantePorIdUserCase);

        doNothing().when(deletaRestaurantePorIdUserCase).deletaPorId(id);

        mockMvc.perform(delete("/restaurantes/{id}", id))
                        .andExpect(status().isNoContent());

        verify(deletaRestaurantePorIdUserCaseFactory, times(1)).buildDeletaRestaurantePorIdUserCase(
                restauranteRepository, restaurantePresenter, reservaRepository);
        verify(deletaRestaurantePorIdUserCase, times(1)).deletaPorId(id);

    }
}