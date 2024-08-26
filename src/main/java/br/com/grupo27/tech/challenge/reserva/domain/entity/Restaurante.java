package br.com.grupo27.tech.challenge.reserva.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

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
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setLocalizacao(localizacao);
        this.setHorarioFuncionamento(horarioFuncionamento);
        this.setCapacidade(capacidade);
        this.setTipoCozinhaList(tipoCozinhas);
        this.setProprietario(proprietario);
    }

    public Restaurante() {
    }


    public String getNome() {
        return nome;
    }

    public Restaurante setNome(String nome) {
        this.validadorNotNullOrEmpty(nome, "Nome");
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Restaurante setDescricao(String descricao) {
        this.validadorNotNullOrEmpty(descricao, "Descrição");
        this.descricao = descricao;
        return this;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Restaurante setLocalizacao(String localizacao) {
        this.validadorNotNullOrEmpty(localizacao, "Localização");
        this.localizacao = localizacao;
        return this;
    }

    public LocalDateTime getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Restaurante setHorarioFuncionamento(LocalDateTime horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
        return this;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Restaurante setCapacidade(int capacidade) {
        this.validadorIsPositive(capacidade, "Capacidade");
        this.capacidade = capacidade;
        return this;
    }

    public List<TipoCozinha> getTipoCozinhaList() {
        return tipoCozinhaList;
    }

    public Restaurante setTipoCozinhaList(List<TipoCozinha> tipoCozinhaList) {
        if (tipoCozinhaList == null) {
            throw new IllegalArgumentException("O Tipo de Cozinha nao pode ser nulo");
        }
        if (tipoCozinhaList.isEmpty()) {
            throw new IllegalArgumentException("O Tipo de Cozinha nao pode estar vazio");
        }
        this.tipoCozinhaList = tipoCozinhaList;
        return this;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public Restaurante setProprietario(Proprietario proprietario) {
        if (proprietario == null) {
            throw new IllegalArgumentException("O Proprietario nao pode ser nulo");
        }
        this.proprietario = proprietario;
        return this;
    }

    private void validadorNotNullOrEmpty(String campo, String nomeCampo) {
        if (campo == null) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser nulo");
        }
        if (campo.isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " não pode estar vazio");
        }
        if (campo.isBlank()) {
            throw new IllegalArgumentException(nomeCampo + " não pode estar em branco");
        }
    }

    private void validadorIsPositive(int numero, String nomeCampo) {
        if (numero < 1) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser menor que 1");
        }
    }
}
