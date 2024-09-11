package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarRestaurantePresenterImpl implements AtualizarRestaurantePresenter {

    private final ModelMapper mapper;

    @Override
    public AtualizarRestauranteInput atualizarRestauranteRequestEmAtualizarRestauranteInput(String id, AtualizarRestauranteRequest atualizarRestauranteRequest) {
        var atualizarRestauranteInput = mapper.map(atualizarRestauranteRequest, AtualizarRestauranteInput.class);
        atualizarRestauranteInput.setId(id);

        return atualizarRestauranteInput;
    }

    @Override
    public Restaurante atualizarRestauranteInputEmRestaurante(AtualizarRestauranteInput atualizarRestauranteInput) {

        return new Restaurante(atualizarRestauranteInput.getNome(), atualizarRestauranteInput.getDescricao(), atualizarRestauranteInput.getLocalizacao(), atualizarRestauranteInput.getHoraAbertura(),
                atualizarRestauranteInput.getHoraFechamento(), atualizarRestauranteInput.getCapacidade(), atualizarRestauranteInput.getTipoCozinhaList(), atualizarRestauranteInput.getProprietarioId());
    }

    @Override
    public AtualizarRestauranteOutput restauranteEmAtualizarRestauranteOutput(Restaurante restaurante) {

        return mapper.map(restaurante, AtualizarRestauranteOutput.class);
    }

    @Override
    public RestauranteResponse atualizarRestauranteOutputEmRestauranteResponse(AtualizarRestauranteOutput atualizarRestauranteOutput) {

        return mapper.map(atualizarRestauranteOutput, RestauranteResponse.class);
    }
}
