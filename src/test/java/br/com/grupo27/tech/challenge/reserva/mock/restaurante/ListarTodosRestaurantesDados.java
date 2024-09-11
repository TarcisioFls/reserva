package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.ListarTodosRestaurentesOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados;
import br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;
import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.MEXICANA;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioAtualizado;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModel;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioModelAtualizado;

public interface ListarTodosRestaurantesDados {

    static PagedModel<ListarTodosRestaurentesOutput> getPageTodosRestaurantesOutput() {
        var restauranteAkira  = new ListarTodosRestaurentesOutput("66c67aa035ed1f735450b7a2", "Restaurante Akira", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(JAPONESA), getProprietario());
        var restauranteTacoTaca = new ListarTodosRestaurentesOutput("66c67aa035ed1f735450b7a3", "Restaurante Taco Taca", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(MEXICANA), getProprietario());
        var todosRestaurantesOutput = List.of(restauranteAkira, restauranteTacoTaca);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodoRestaurantesOutput = new PageImpl<>(todosRestaurantesOutput, pageRequest, todosRestaurantesOutput.size());

        return new PagedModel<>(pageTodoRestaurantesOutput);
    }

    static PagedModel<RestauranteResponse> getPageRestauranteResponse() {
        var restauranteAkira  = new RestauranteResponse("66c67aa035ed1f735450b7a2", "Restaurante Akira", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(JAPONESA), getProprietario());
        var restauranteTacoTaca = new RestauranteResponse("66c67aa035ed1f735450b7a3", "Restaurante Taco Taca", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(MEXICANA), getProprietario());
        var todosRestaurantesOutput = List.of(restauranteAkira, restauranteTacoTaca);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodoRestaurantesOutput = new PageImpl<>(todosRestaurantesOutput, pageRequest, todosRestaurantesOutput.size());

        return new PagedModel<>(pageTodoRestaurantesOutput);
    }

    static Page<RestauranteModel> getPageRestauranteModel() {
        var restauranteAkira  = new RestauranteModel("66c67aa035ed1f735450b7a2", "Restaurante Akira", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(JAPONESA), getProprietarioModel());
        var restauranteTacoTaca = new RestauranteModel("66c67aa035ed1f735450b7a3","Restaurante Taco Taca", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(MEXICANA), getProprietarioModelAtualizado());
        var todosRestaurantesOutput = List.of(restauranteAkira, restauranteTacoTaca);

        return new PageImpl<>(todosRestaurantesOutput);
    }

    static PagedModel<Restaurante> getPageRestaurante() {
        var restauranteAkira  = new Restaurante("66c67aa035ed1f735450b7a2", "Restaurante Akira", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(JAPONESA), getProprietario());
        var restauranteTacoTaca = new Restaurante("66c67aa035ed1f735450b7a3", "Restaurante Taco Taca", "descricao", "localizacao", LocalTime.of(8, 0), LocalTime.of(20, 0), 20, List.of(MEXICANA), getProprietarioAtualizado());
        var todosRestaurantesOutput = List.of(restauranteAkira, restauranteTacoTaca);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodoRestaurantesOutput = new PageImpl<>(todosRestaurantesOutput, pageRequest, todosRestaurantesOutput.size());

        return new PagedModel<>(pageTodoRestaurantesOutput);
    }

}
