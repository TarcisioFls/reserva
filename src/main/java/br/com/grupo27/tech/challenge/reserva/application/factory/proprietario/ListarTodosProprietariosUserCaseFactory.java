package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ListarTodosProprietariosUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.ListarTodosProprietariosAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ListarTodosProprietariosUserCaseFactory {

    public ListarTodosProprietariosUserCase buildListarTodosProprietariosUserCase(ListarTodosProprietariosPresenter listarTodosProprietariosPresenter,
                                                                                  ProprietarioPresenter proprietarioPresenter,
                                                                                  ProprietarioRepository proprietarioRepository) {
        return new ListarTodosProprietariosUserCase(
                buildListarTodosProprietariosGateway(proprietarioPresenter, proprietarioRepository),
                listarTodosProprietariosPresenter
        );
    }

    private ListarTodosProprietariosAdapter buildListarTodosProprietariosGateway(ProprietarioPresenter proprietarioPresenter,
                                                                                 ProprietarioRepository proprietarioRepository) {
        return new ListarTodosProprietariosAdapter(proprietarioRepository, proprietarioPresenter);
    }

}
