package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.CriarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CriarRestauranteUserCase {

    private final CriarRestauranteGateway criarRestauranteGateway;
    private final CriarRestaurantePresenter criarRestaurantePresenter;

    public CriarRestauranteOutput criar(CriarRestauranteInput criarRestauranteInput) {
        var restaurante = criarRestaurantePresenter.criarRestauranteInputParaRestaurante(criarRestauranteInput);
        restaurante = criarRestauranteGateway.criar(restaurante);

        return criarRestaurantePresenter.restauranteParaCriarRestauranteOutput(restaurante);
    }
}
