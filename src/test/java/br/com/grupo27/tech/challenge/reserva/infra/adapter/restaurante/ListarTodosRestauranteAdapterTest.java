package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestauranteModel;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarTodosRestauranteAdapterTest extends TesteConfig {

    @InjectMocks
    private ListarTodosRestauranteAdapter listarTodosRestauranteAdapter;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @Test
    void testeListarTodos() {

        var pageRequest = PageRequest.of(0, 10);
        var pageRestauranteModel = getPageRestauranteModel();

        when(restauranteRepository.findAll(pageRequest)).thenReturn(pageRestauranteModel);
        when(restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(pageRestauranteModel)).thenReturn(getPageRestaurante());

        var resultado = listarTodosRestauranteAdapter.listarTodos(pageRequest);

        assertAll("Validando o retorno da paginação de restaurantes",
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll("Validando o conteúdo da página de restaurantes",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0)).getId()),
                () -> assertEquals("Restaurante Akira", requireNonNull(resultado.getContent().get(0)).getNome()),
                () -> assertEquals("66c67aa035ed1f735450b7a3", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("Restaurante Taco Taca", requireNonNull(resultado.getContent().get(1)).getNome())
        );

        verify(restauranteRepository, times(1)).findAll(pageRequest);
        verify(restaurantePresenter, times(1)).pageRestauranteModelListEmPageRestauranteList(pageRestauranteModel);

    }

    @Test
    void testeListarTodosNull() {

        var pageRequest = PageRequest.of(0, 10);

        when(restauranteRepository.findAll(pageRequest)).thenReturn(Page.empty());
        when(restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(Page.empty())).thenReturn(null);

        var resultado = listarTodosRestauranteAdapter.listarTodos(pageRequest);

        assertNull(resultado);

        verify(restauranteRepository, times(1)).findAll(pageRequest);
        verify(restaurantePresenter, times(1)).pageRestauranteModelListEmPageRestauranteList(Page.empty());

    }

    @Test
    void testeListarTodosVazio() {

        var pageRequest = PageRequest.of(0, 10);

        when(restauranteRepository.findAll(pageRequest)).thenReturn(Page.empty());
        when(restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(Page.empty())).thenReturn(new PagedModel<>(Page.empty()));

        var resultado = listarTodosRestauranteAdapter.listarTodos(pageRequest);

        assertNotNull(resultado);
        assertTrue(requireNonNull(resultado.getContent()).isEmpty());

        verify(restauranteRepository, times(1)).findAll(pageRequest);
        verify(restaurantePresenter, times(1)).pageRestauranteModelListEmPageRestauranteList(Page.empty());

    }

}