package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.ListarTodosProprietariosGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarTodosProprietariosAdapter implements ListarTodosProprietariosGateway {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioPresenter proprietarioPresenter;

    @Override
    public PagedModel<Proprietario> listarTodos(PageRequest paginacaoRequest) {

        var pageProprietarioModelList = proprietarioRepository.findAll(paginacaoRequest);

        return proprietarioPresenter.pageProprietarioModelListEmPageProprietarioList(pageProprietarioModelList);
    }

}
