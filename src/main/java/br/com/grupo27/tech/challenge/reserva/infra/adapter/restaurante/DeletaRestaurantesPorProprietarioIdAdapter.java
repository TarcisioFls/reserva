package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantesPorProprietarioIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaRestaurantesPorProprietarioIdAdapter implements DeletaRestaurantesPorProprietarioIdGateway {

    private final RestauranteRepository restauranteRepository;

    @Override
    public void deletaPorProprietarioId(String id) {
        restauranteRepository.deleteByProprietarioId(id);
    }
}
