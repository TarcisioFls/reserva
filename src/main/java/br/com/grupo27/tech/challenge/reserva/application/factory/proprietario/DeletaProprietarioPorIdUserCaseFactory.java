package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.DeletaProprietarioPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.DeletaProprietarioPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaProprietarioPorIdUserCaseFactory {

    private final BuscarProprietarioPorIdUserCaseFactory buscarProprietarioPorIdUserCaseFactory;

    public DeletaProprietarioPorIdUserCase buildDeletaProprietarioPorIdUserCase(ProprietarioPresenter proprietarioPresenter,
                                                                                ProprietarioRepository proprietarioRepository) {
        return new DeletaProprietarioPorIdUserCase(
                buildDeletaProprietarioPorIdGateway(proprietarioRepository),
                buscarProprietarioPorIdUserCaseFactory.buildBuscarProprietarioPorIdGateway(proprietarioPresenter, proprietarioRepository)
        );

    }

    public DeletaProprietarioPorIdAdapter buildDeletaProprietarioPorIdGateway(ProprietarioRepository proprietarioRepository) {
        return new DeletaProprietarioPorIdAdapter(proprietarioRepository);
    }

}
