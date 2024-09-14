package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.BuscarProprietarioPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.BuscarProprietarioPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.BuscarProprietarioPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarProprietarioPorIdUserCaseFactory {

    public BuscarProprietarioPorIdUserCase buildBuscarProprietarioPorIdUserCase(BuscarProprietarioPorIdPresenter buscarProprietarioPorIdPresenter,
                                                                                ProprietarioPresenter proprietarioPresenter,
                                                                                ProprietarioRepository proprietarioRepository) {
        return new BuscarProprietarioPorIdUserCase(
                buildBuscarProprietarioPorIdGateway(proprietarioPresenter, proprietarioRepository),
                buscarProprietarioPorIdPresenter
        );
    }

    public BuscarProprietarioPorIdAdapter buildBuscarProprietarioPorIdGateway(ProprietarioPresenter proprietarioPresenter,
                                                                               ProprietarioRepository proprietarioRepository) {
        return new BuscarProprietarioPorIdAdapter(proprietarioRepository, proprietarioPresenter);
    }

}
