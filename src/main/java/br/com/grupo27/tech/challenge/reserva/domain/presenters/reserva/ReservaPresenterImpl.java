package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservaPresenterImpl implements  ReservaPresenter{

    private final ModelMapper mapper;

    @Override
    public Reserva reservaModelEmReserva(ReservaModel reservaModel) {

        return new Reserva(reservaModel.getId(), reservaModel.getDataHora(), reservaModel.getQuantidadePessoas(),
                reservaModel.getRestauranteId(), reservaModel.getClienteId(), reservaModel.getStatus());
    }

    @Override
    public ReservaModel reservaEmReservaModel(Reserva reserva) {

        return mapper.map(reserva, ReservaModel.class);
    }

    @Override
    public PagedModel<Reserva> pageReservaModelListEmPageReservaList(Page<ReservaModel> pageReservaModelList) {
        var pageReservas = pageReservaModelList.map(this::reservaModelEmReserva);

        return new PagedModel<>(pageReservas);
    }

}
