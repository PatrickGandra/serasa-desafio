package com.serasa.desafio.dto;

import java.time.LocalDate;

public class PessoaDTO {

    private Long id;
    private LocalDate dataInclusao;
    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private String regiao;
    private int score;

    public PessoaDTO() {
    }

    public PessoaDTO(final Long id, final LocalDate dataInclusao, final String nome, final String telefone, final int idade, final String cidade, final String estado,
                     final String regiao, final int score) {
        this.id = id;
        this.dataInclusao = dataInclusao;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
        this.regiao = regiao;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(final LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(final int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(final String regiao) {
        this.regiao = regiao;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }
}
