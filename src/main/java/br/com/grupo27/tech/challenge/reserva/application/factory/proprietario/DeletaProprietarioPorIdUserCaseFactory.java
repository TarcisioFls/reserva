package br.com.grupo27.tech.challenge.reserva.application.factory.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.DeletaProprietarioPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.BuscarProprietarioPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario.DeletaProprietarioPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.DeletaRestaurantesPorProprietarioIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaProprietarioPorIdUserCaseFactory {

    private final BuscarProprietarioPorIdUserCaseFactory buscarProprietarioPorIdUserCaseFactory;

    public DeletaProprietarioPorIdUserCase buildDeletaProprietarioPorIdUserCase(ProprietarioPresenter proprietarioPresenter,
                                                                                ProprietarioRepository proprietarioRepository,
                                                                                RestauranteRepository restauranteRepository) {
        return new DeletaProprietarioPorIdUserCase(
                buildDeletaProprietarioPorIdGateway(proprietarioRepository),
                buildBuscarProprietarioPorIdGateway(proprietarioRepository, proprietarioPresenter),
                buildDeletaRestaurantePorIdGateway(restauranteRepository)
        );

    }

    private DeletaProprietarioPorIdAdapter buildDeletaProprietarioPorIdGateway(ProprietarioRepository proprietarioRepository) {
        return new DeletaProprietarioPorIdAdapter(proprietarioRepository);
    }

    private BuscarProprietarioPorIdAdapter buildBuscarProprietarioPorIdGateway(ProprietarioRepository proprietarioRepository,
                                                                               ProprietarioPresenter proprietarioPresenter) {
        return new BuscarProprietarioPorIdAdapter(proprietarioRepository, proprietarioPresenter);
    }

    private DeletaRestaurantesPorProprietarioIdAdapter buildDeletaRestaurantePorIdGateway(RestauranteRepository restauranteRepository) {
        return new DeletaRestaurantesPorProprietarioIdAdapter(restauranteRepository);
    }

}
