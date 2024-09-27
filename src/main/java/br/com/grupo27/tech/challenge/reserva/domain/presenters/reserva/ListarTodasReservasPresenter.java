package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarTodasReservasOutput;
import org.springframework.data.web.PagedModel;

public interface ListarTodasReservasPresenter {

    PagedModel<ReservaResponse> pageTodasReservasOutputEmPageReservasResponse(PagedModel<ListarTodasReservasOutput> pageReservaOutput);

    PagedModel<ListarTodasReservasOutput> pageReservaEmPageTodosProprietariosOutput(PagedModel<Reserva> paginacaoReserva);
}

