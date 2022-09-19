package com.serasa.desafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreDTO {

    private String descricao;
    private int inicial;
    @JsonProperty("final")
    private int fim;

    public ScoreDTO() {
    }

    public ScoreDTO(final String descricao, final int inicial, final int fim) {
        this.descricao = descricao;
        this.inicial = inicial;
        this.fim = fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public int getInicial() {
        return inicial;
    }

    public void setInicial(final int inicial) {
        this.inicial = inicial;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(final int fim) {
        this.fim = fim;
    }
}
