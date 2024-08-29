package br.com.grupo27.tech.challenge.reserva.infra.model;

import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "restaurantes")
public class RestauranteModel {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private String descricao;
    private String localizacao;
    private String horarioFuncionamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private ProprietarioModel proprietario;
}
