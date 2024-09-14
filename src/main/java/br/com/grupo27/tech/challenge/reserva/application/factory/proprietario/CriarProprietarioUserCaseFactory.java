package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.CriarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.CriarProprietarioAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CriarProprietarioUserCaseFactory {

    public CriarProprietarioUserCase buildCriarProprietarioUserCase(CriarProprietarioPresenter criarProprietarioPresenter,
                                                                    ProprietarioPresenter proprietarioPresenter,
                                                                    ProprietarioRepository proprietarioRepository) {
        return new CriarProprietarioUserCase(
                buildCriarProprietarioGateway(proprietarioPresenter, proprietarioRepository),
                criarProprietarioPresenter
        );
    }

    private CriarProprietarioAdapter buildCriarProprietarioGateway(ProprietarioPresenter proprietarioPresenter,
                                                                   ProprietarioRepository proprietarioRepository) {
        return new CriarProprietarioAdapter(proprietarioRepository, proprietarioPresenter);
    }

}
