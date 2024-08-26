package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteResponse {

    private String id;
    private String nome;
    private String descricao;
    private String localizacao;
    private LocalDateTime horarioFuncionamento;
    private int capacidade;
    //    private List<TipoCozinha> tipoCozinha;
    private Proprietario proprietario;
}
