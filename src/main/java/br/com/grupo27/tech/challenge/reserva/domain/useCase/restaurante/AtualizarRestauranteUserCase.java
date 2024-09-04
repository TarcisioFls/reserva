package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.AtualizarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.AtualizarRestaurantePresenter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class AtualizarRestauranteUserCase {

    private final AtualizarRestauranteGateway atualizarRestauranteGateway;
    private final AtualizarRestaurantePresenter atualizarRestaurantePresenter;

    public AtualizarRestauranteOutput atualizar(AtualizarRestauranteInput atualizarRestauranteInput) {
        var restaurante = atualizarRestauranteGateway.buscarPorId(atualizarRestauranteInput.getId())
                .orElseThrow(() -> new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO));

        restaurante = atualizarRestaurantePresenter.atualizarRestauranteInputEmRestaurante(atualizarRestauranteInput);
        restaurante = atualizarRestauranteGateway.atualizar(restaurante);

        return atualizarRestaurantePresenter.restauranteEmAtualizarRestauranteOutput(restaurante);
    }
}
