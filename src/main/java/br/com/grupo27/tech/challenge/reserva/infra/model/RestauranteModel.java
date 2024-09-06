package br.com.grupo27.tech.challenge.reserva.infra.model;

import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "restaurantes")
public class RestauranteModel {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private String descricao;
    private String localizacao;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private ProprietarioModel proprietario;
}
