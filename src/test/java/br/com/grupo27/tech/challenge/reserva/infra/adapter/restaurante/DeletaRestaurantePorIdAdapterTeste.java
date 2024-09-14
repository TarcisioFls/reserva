package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletaRestaurantePorIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private DeletaRestaurantePorIdAdapter deletaRestaurantePorIdAdapter;

    @Mock
    private RestauranteRepository restauranteRepository;


    @Test
    void testaDeletaPorId() {

        var id = "66c67aa035ed1f735450b7a2";

        deletaRestaurantePorIdAdapter.deletaPorId(id);

        verify(restauranteRepository, times(1)).deleteById(id);

    }
}