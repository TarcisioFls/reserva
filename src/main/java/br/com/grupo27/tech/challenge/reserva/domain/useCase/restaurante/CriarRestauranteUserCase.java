package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.BuscarProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.CriarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_NAO_ENCONTRADO;

@Getter
@RequiredArgsConstructor
public class CriarRestauranteUserCase {

    private final CriarRestauranteGateway criarRestauranteGateway;
    private final CriarRestaurantePresenter criarRestaurantePresenter;
    private final BuscarProprietarioPorIdGateway buscarProprietarioPorIdGateway;

    public CriarRestauranteOutput criar(CriarRestauranteInput criarRestauranteInput) {
        var restaurante = criarRestaurantePresenter.criarRestauranteInputParaRestaurante(criarRestauranteInput);

        buscarProprietarioPorIdGateway.buscarPorId(criarRestauranteInput.getProprietarioId()).orElseThrow(
                () -> new ExceptionAdvice(PROPRIETARIO_NAO_ENCONTRADO));

        restaurante = criarRestauranteGateway.criar(restaurante);

        return criarRestaurantePresenter.restauranteParaCriarRestauranteOutput(restaurante);
    }
}
