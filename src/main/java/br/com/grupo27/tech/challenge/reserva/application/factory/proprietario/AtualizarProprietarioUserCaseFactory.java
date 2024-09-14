package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.AtualizarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.AtualizarProprietarioAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AtualizarProprietarioUserCaseFactory {

    public AtualizarProprietarioUserCase buildAtualizarProprietarioUserCase(AtualizarProprietarioPresenter atualizarProprietarioPresenter,
                                                                            ProprietarioPresenter proprietarioPresenter,
                                                                            ProprietarioRepository proprietarioRepository) {
        return new AtualizarProprietarioUserCase(
                buildAtualizarProprietarioGateway(proprietarioPresenter, proprietarioRepository),
                atualizarProprietarioPresenter
        );
    }

    private AtualizarProprietarioAdapter buildAtualizarProprietarioGateway(ProprietarioPresenter proprietarioPresenter,
                                                                           ProprietarioRepository proprietarioRepository) {
        return new AtualizarProprietarioAdapter(proprietarioRepository, proprietarioPresenter);
    }

}
