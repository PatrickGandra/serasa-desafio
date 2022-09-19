package com.serasa.desafio.dto;

import java.util.List;

public class PessoaConsultaDTO {

    private String nome;
    private String cidade;
    private String telefone;
    private int idade;
    private String scoreDescricao;
    private List<String> estados;

    public PessoaConsultaDTO() {
    }

    public PessoaConsultaDTO(final String nome, final String cidade, final String telefone, final int idade, final String scoreDescricao,
                             final List<String> estados) {
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
        this.idade = idade;
        this.scoreDescricao = scoreDescricao;
        this.estados = estados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
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

    public String getScoreDescricao() {
        return scoreDescricao;
    }

    public void setScoreDescricao(final String scoreDescricao) {
        this.scoreDescricao = scoreDescricao;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(final List<String> estados) {
        this.estados = estados;
    }
}
