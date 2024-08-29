package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class Restaurante {

    private String nome;
    private String descricao;
    private String localizacao;
    private LocalDateTime horarioFuncionamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private Proprietario proprietario;

    public Restaurante(String nome,
                       String descricao,
                       String localizacao,
                       LocalDateTime horarioFuncionamento,
                       Integer capacidade,
                       List<TipoCozinha> tipoCozinhas,
                       Proprietario proprietario) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(CodigoError.NOME_OBRIGATORIO);
        }

        this.setNome(nome);
        this.setDescricao(descricao);
        this.setLocalizacao(localizacao);
        this.setHorarioFuncionamento(horarioFuncionamento);
        this.setCapacidade(capacidade);
        this.setTipoCozinhaList(tipoCozinhas);
        this.setProprietario(proprietario);
    }

//    public Restaurante setNome(String nome) {
//        this.validadorNotNullOrEmpty(nome, "Nome");
//        this.nome = nome;
//        return this;
//    }
//
//    public Restaurante setDescricao(String descricao) {
//        this.validadorNotNullOrEmpty(descricao, "Descrição");
//        this.descricao = descricao;
//        return this;
//    }
//
//    public Restaurante setLocalizacao(String localizacao) {
//        this.validadorNotNullOrEmpty(localizacao, "Localização");
//        this.localizacao = localizacao;
//        return this;
//    }
//
//    public Restaurante setHorarioFuncionamento(LocalDateTime horarioFuncionamento) {
//        this.horarioFuncionamento = horarioFuncionamento;
//        return this;
//    }
//
//    public Restaurante setCapacidade(int capacidade) {
//        this.validadorIsPositive(capacidade, "Capacidade");
//        this.capacidade = capacidade;
//        return this;
//    }
//
//    public Restaurante setTipoCozinhaList(List<TipoCozinha> tipoCozinhaList) {
//        if (tipoCozinhaList == null) {
//            throw new IllegalArgumentException("O Tipo de Cozinha nao pode ser nulo");
//        }
//        if (tipoCozinhaList.isEmpty()) {
//            throw new IllegalArgumentException("O Tipo de Cozinha nao pode estar vazio");
//        }
//        this.tipoCozinhaList = tipoCozinhaList;
//        return this;
//    }
//
//    public Restaurante setProprietario(Proprietario proprietario) {
//        if (proprietario == null) {
//            throw new IllegalArgumentException("O Proprietario nao pode ser nulo");
//        }
//        this.proprietario = proprietario;
//        return this;
//    }
//
//    private void validadorNotNullOrEmpty(String campo, String nomeCampo) {
//        if (campo == null) {
//            throw new IllegalArgumentException(nomeCampo + " não pode ser nulo");
//        }
//        if (campo.isEmpty()) {
//            throw new IllegalArgumentException(nomeCampo + " não pode estar vazio");
//        }
//        if (campo.isBlank()) {
//            throw new IllegalArgumentException(nomeCampo + " não pode estar em branco");
//        }
//    }
//
//    private void validadorIsPositive(int numero, String nomeCampo) {
//        if (numero < 1) {
//            throw new IllegalArgumentException(nomeCampo + " não pode ser menor que 1");
//        }
//    }
}
