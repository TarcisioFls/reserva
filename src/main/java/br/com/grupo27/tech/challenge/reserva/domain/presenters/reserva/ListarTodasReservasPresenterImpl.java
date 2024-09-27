package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarTodasReservasOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarTodasReservasPresenterImpl implements ListarTodasReservasPresenter {

    private final ModelMapper mapper;

    @Override
    public PagedModel<ReservaResponse> pageTodasReservasOutputEmPageReservasResponse(PagedModel<ListarTodasReservasOutput> pageReservaOutput) {
        var reservaResponseList = pageReservaOutput.getContent().stream()
                .map(reservaOutput -> mapper.map(reservaOutput, ReservaResponse.class))
                .toList();
        var metadata = pageReservaOutput.getMetadata();
        var pageReservaResponse = new PageImpl<>(reservaResponseList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageReservaResponse);
    }

    @Override
    public PagedModel<ListarTodasReservasOutput> pageReservaEmPageTodosProprietariosOutput(PagedModel<Reserva> paginacaoReserva) {
        var listarTodosProprietariosOutputList = paginacaoReserva.getContent().stream()
                .map(reserva -> mapper.map(reserva, ListarTodasReservasOutput.class))
                .toList();

        var metadata = paginacaoReserva.getMetadata();
        var pageListarTodosProprietariosOutput = new PageImpl<>(listarTodosProprietariosOutputList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageListarTodosProprietariosOutput);
    }
}
