package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarTodasReservasGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarTodasReservasOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarTodasReservasPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarTodasReservasUserCase {

    private final ListarTodasReservasGateway listarTodasReservasGateway;
    private final ListarTodasReservasPresenter listarTodasReservasPresenter;

    public PagedModel<ListarTodasReservasOutput> listarTodos(int pagina, int tamanho) {
        var paginacaoReserva = listarTodasReservasGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return listarTodasReservasPresenter.pageReservaEmPageTodosProprietariosOutput(paginacaoReserva);
    }
}
