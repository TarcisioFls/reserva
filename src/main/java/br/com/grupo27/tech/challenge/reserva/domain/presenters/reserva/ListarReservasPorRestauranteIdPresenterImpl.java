package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorRestauranteIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarReservasPorRestauranteIdPresenterImpl implements ListarReservasPorRestauranteIdPresenter {

    private final ModelMapper mapper;

    @Override
    public ListarReservasPorRestauranteIdInput getListarReservasPorRestauranteInput(int pagina, int tamanho, String restauranteId) {
        return new ListarReservasPorRestauranteIdInput(pagina, tamanho, restauranteId);
    }

    @Override
    public PagedModel<ReservaResponse> listarReservasPorRestauranteIdOutputPageModelEmReservaResponsePageModel(PagedModel<ListarReservasPorRestauranteIdOutput> listarReservasPorRestauranteIdOutputPagedModel) {
        var reservaResponseList = listarReservasPorRestauranteIdOutputPagedModel.getContent().stream()
                .map(reservaOutput -> mapper.map(reservaOutput, ReservaResponse.class))
                .toList();

        return new PagedModel<>(new PageImpl<>(reservaResponseList));
    }

    @Override
    public PagedModel<ListarReservasPorRestauranteIdOutput> pageReservaModelEmPageReservaOutput(PagedModel<Reserva> reservaPagedModel) {

        var reservaOutputList = reservaPagedModel.getContent().stream()
                .map(reserva -> mapper.map(reserva, ListarReservasPorRestauranteIdOutput.class))
                .toList();

        return new PagedModel<>(new PageImpl<>(reservaOutputList));
    }
}
