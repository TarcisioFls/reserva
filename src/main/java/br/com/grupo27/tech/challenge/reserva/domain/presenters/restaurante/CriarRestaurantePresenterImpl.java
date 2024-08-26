package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarRestaurantePresenterImpl implements CriarRestaurantePresenter {

    private final ModelMapper mapper;


    @Override
    public Restaurante criarRestauranteInputParaRestaurante(CriarRestauranteInput criarRestauranteInput) {
        return null;
    }

    @Override
    public CriarRestauranteOutput restauranteParaCriarRestauranteOutput(Restaurante restaurante) {
        return null;
    }

    @Override
    public CriarRestauranteInput criarRestauranteParaCriarRestauranteInput(CriarRestauranteRequest request) {
        return null;
    }

    @Override
    public RestauranteResponse criarRestauranteOutputParaRestauranteResponse(CriarRestauranteOutput restauranteOutput) {
        return null;
    }
}
