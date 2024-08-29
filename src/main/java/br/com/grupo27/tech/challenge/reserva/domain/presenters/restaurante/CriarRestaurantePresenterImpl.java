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
        return new Restaurante(criarRestauranteInput.getNome(), criarRestauranteInput.getDescricao(),
                criarRestauranteInput.getLocalizacao(), criarRestauranteInput.getHorarioFuncionamento(),
                criarRestauranteInput.getCapacidade(), criarRestauranteInput.getTipoCozinha(),
                criarRestauranteInput.getProprietario());
    }

    @Override
    public CriarRestauranteOutput restauranteParaCriarRestauranteOutput(Restaurante restaurante) {
        return new CriarRestauranteOutput(restaurante.getNome(), restaurante.getDescricao(),
                restaurante.getLocalizacao(), restaurante.getHorarioFuncionamento(),
                restaurante.getCapacidade(), restaurante.getTipoCozinhaList(),
                restaurante.getProprietario());
    }

    @Override
    public CriarRestauranteInput criarRestauranteParaCriarRestauranteInput(CriarRestauranteRequest request) {
        return new CriarRestauranteInput(request.getNome(), request.getDescricao(),
                request.getLocalizacao(), request.getHorarioFuncionamento(),
                request.getCapacidade(), request.getTipoCozinha(),
                request.getProprietario());
    }

    @Override
    public RestauranteResponse criarRestauranteOutputParaRestauranteResponse(CriarRestauranteOutput restauranteOutput) {
        return new RestauranteResponse(restauranteOutput.getNome(), restauranteOutput.getDescricao(),
                restauranteOutput.getLocalizacao(), restauranteOutput.getHorarioFuncionamento(),
                restauranteOutput.getCapacidade(), restauranteOutput.getTipoCozinha(),
                restauranteOutput.getProprietario());
    }
}
