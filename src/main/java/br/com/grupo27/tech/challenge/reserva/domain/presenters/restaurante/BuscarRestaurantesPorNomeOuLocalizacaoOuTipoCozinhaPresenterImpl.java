package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenterImpl implements BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter {

    private final ModelMapper mapper;

    @Override
    public PagedModel<RestauranteResponse> buscarRestaurantesOutputEmRestaurantesResponse(PagedModel<BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput> buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutputList) {
        var restauranteResponseList = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutputList.getContent().stream()
                .map(buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput -> mapper.map(buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput, RestauranteResponse.class))
                .toList();

        var metadata = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutputList.getMetadata();
        var pageRestauranteResponse = new PageImpl<>(restauranteResponseList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageRestauranteResponse);
    }

    @Override
    public PagedModel<BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput> restauranteEmBuscarRestauranteOutput(PagedModel<Restaurante> paginacaoRestaurante) {

        var buscarRestaurantesOutputsList = paginacaoRestaurante.getContent().stream()
                .map(restaurante -> mapper.map(restaurante, BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput.class))
                .toList();
        var metadata = paginacaoRestaurante.getMetadata();
        var buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput = new PageImpl<>(buscarRestaurantesOutputsList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput);
    }
}
