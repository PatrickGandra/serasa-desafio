package com.serasa.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @Column(name = "descricao", length = 12, nullable = false)
    private String descricao;
    @Column(name = "inicial", nullable = false)
    private int inicial;
    @Column(name = "fim", nullable = false)
    private int fim;

    public Score() {
    }

    public Score(final String descricao, final int inicial, final int fim) {
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
