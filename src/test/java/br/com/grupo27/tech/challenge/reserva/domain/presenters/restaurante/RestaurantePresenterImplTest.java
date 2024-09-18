package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestauranteModel;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantePresenterImplTest {

    private Restaurante restaurante;
    private RestauranteModel restauranteModel;
    private AutoCloseable closeable;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private RestaurantePresenterImpl restaurantePresenter;

    private Page<RestauranteModel> pageRestauranteModel;

    @BeforeEach
    void setUp() {
       closeable = MockitoAnnotations.openMocks(this);
        restaurante = getRestaurante();
        restauranteModel = getRestauranteModel();
        pageRestauranteModel = getPageRestauranteModel();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testRestauranteParaRestauranteModel() {
        var resultado = restaurantePresenter.restauranteParaRestauranteModel(restaurante);

        assertEquals(restauranteModel, resultado);
    }

    @Test
    void testRestauranteModelParaRestaurante() {
        var resultado = restaurantePresenter.restauranteModelParaRestaurante(restauranteModel);

        assertEquals(restaurante.getNome(), resultado.getNome());
    }

    @Test
    void testPageRestauranteModelListEmPageRestauranteList() {

        var restauranteModelEsperado = getPageRestauranteModel();

        var resultado = restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(pageRestauranteModel);

        assertAll("Tete PagedModel",
                () -> assertEquals(restauranteModelEsperado.getContent().size(), resultado.getContent().size()),
                () -> assertEquals(restauranteModelEsperado.getNumber(), resultado.getMetadata().number()),
                () -> assertEquals(restauranteModelEsperado.getSize(), resultado.getMetadata().size()),
                () -> assertEquals(restauranteModelEsperado.getTotalElements(), resultado.getMetadata().totalElements())
        );

        assertAll("Teste PagedModel.content()",
                () -> assertEquals(restauranteModelEsperado.getContent().get(0).getId(), resultado.getContent().get(0).getId()),
                () -> assertEquals(restauranteModelEsperado.getContent().get(0).getNome(), resultado.getContent().get(0).getNome()),
                () -> assertEquals(restauranteModelEsperado.getContent().get(1).getId(), resultado.getContent().get(1).getId()),
                () -> assertEquals(restauranteModelEsperado.getContent().get(1).getNome(), resultado.getContent().get(1).getNome())
        );
    }
}