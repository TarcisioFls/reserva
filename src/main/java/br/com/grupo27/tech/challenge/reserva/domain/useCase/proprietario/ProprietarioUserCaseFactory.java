package br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.AtualizarProprietarioAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.CriarProprietarioAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.ListarTodosProprietariosAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProprietarioUserCaseFactory {

    public AtualizarProprietarioUserCase buildAtualizarProprietarioUserCase(AtualizarProprietarioPresenter atualizarProprietarioPresenter,
                                                                            ProprietarioPresenter proprietarioPresenter,
                                                                            ProprietarioRepository proprietarioRepository) {
        return new AtualizarProprietarioUserCase(
                buildAtualizarProprietarioGateway(proprietarioPresenter, proprietarioRepository),
                atualizarProprietarioPresenter
        );
    }

    private AtualizarProprietarioAdapter buildAtualizarProprietarioGateway(ProprietarioPresenter proprietarioPresenter, ProprietarioRepository proprietarioRepository) {
        return new AtualizarProprietarioAdapter(proprietarioRepository, proprietarioPresenter);
    }

    public CriarProprietarioUserCase buildCriarProprietarioUserCase(CriarProprietarioPresenter criarProprietarioPresenter,
                                                                    ProprietarioPresenter proprietarioPresenter,
                                                                    ProprietarioRepository proprietarioRepository) {
        return new CriarProprietarioUserCase(
                buildCriarProprietarioGateway(proprietarioPresenter, proprietarioRepository),
                criarProprietarioPresenter
        );
    }

    private CriarProprietarioAdapter buildCriarProprietarioGateway(ProprietarioPresenter proprietarioPresenter, ProprietarioRepository proprietarioRepository) {
        return new CriarProprietarioAdapter(proprietarioRepository, proprietarioPresenter);
    }

    public ListarTodosProprietariosUserCase buildListarTodosProprietariosUserCase(ListarTodosProprietariosPresenter listarTodosProprietariosPresenter,
                                                                                  ProprietarioPresenter proprietarioPresenter,
                                                                                  ProprietarioRepository proprietarioRepository) {
        return new ListarTodosProprietariosUserCase(
                buildListarTodosProprietariosGateway(proprietarioPresenter, proprietarioRepository),
                listarTodosProprietariosPresenter
        );
    }

    private ListarTodosProprietariosAdapter buildListarTodosProprietariosGateway(ProprietarioPresenter proprietarioPresenter, ProprietarioRepository proprietarioRepository) {
        return new ListarTodosProprietariosAdapter(proprietarioRepository, proprietarioPresenter);
    }

}
