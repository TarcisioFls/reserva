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
    public Restaurante criarRestauranteInputParaRestaurante(CriarRestauranteInput input) {

        return mapper.map(input, Restaurante.class);
    }

    @Override
    public CriarRestauranteOutput restauranteParaCriarRestauranteOutput(Restaurante restaurante) {

        return mapper.map(restaurante, CriarRestauranteOutput.class);
    }

    @Override
    public CriarRestauranteInput criarRestauranteParaCriarRestauranteInput(CriarRestauranteRequest request) {

        return mapper.map(request, CriarRestauranteInput.class);
    }

    @Override
    public RestauranteResponse criarRestauranteOutputParaRestauranteResponse(CriarRestauranteOutput output) {

        return mapper.map(output, RestauranteResponse.class);
    }
}
