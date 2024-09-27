package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorClienteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorClienteIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarReservasPorClienteIdPresenterImpl implements ListarReservasPorClienteIdPresenter {

    private final ModelMapper mapper;

    @Override
    public ListarReservasPorClienteIdInput getListarReservasPorClienteIdInput(int pagina, int tamanho, String clienteId) {
        return new ListarReservasPorClienteIdInput (pagina, tamanho, clienteId);
    }

    @Override
    public PagedModel<ReservaResponse> listarReservasPorClienteIdOutputPageModelEmReservaResponsePageModel(PagedModel<ListarReservasPorClienteIdOutput> listarReservasPorClienteIdOutputPagedModel) {
        var reservaResponseList = listarReservasPorClienteIdOutputPagedModel.getContent().stream()
                .map(reservaOutput -> mapper.map(reservaOutput, ReservaResponse.class))
                .toList();

        return new PagedModel<>(new PageImpl<>(reservaResponseList));
    }

    @Override
    public PagedModel<ListarReservasPorClienteIdOutput> pageReservaModelEmPageReservaOutput(PagedModel<Reserva> reservaPagedModel) {

        var reservaOutputList = reservaPagedModel.getContent().stream()
                .map(reserva -> mapper.map(reserva, ListarReservasPorClienteIdOutput.class))
                .toList();

        return new PagedModel<>(new PageImpl<>(reservaOutputList));
    }
}
