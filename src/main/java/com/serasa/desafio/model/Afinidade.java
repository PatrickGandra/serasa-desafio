package com.serasa.desafio.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "afinidade")
public class Afinidade {

    @Id
    @Column(name = "regiao", length = 12, nullable = false)
    private String regiao;
    @Column(name = "estados", nullable = false)
    private String estados;

    public Afinidade() {
    }

    public Afinidade(final String regiao, final String estados) {
        this.regiao = regiao;
        this.estados = estados;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(final String regiao) {
        this.regiao = regiao;
    }

    public List<String> getEstados() {
        return Arrays.asList(estados.split(","));
    }

    public void setEstados(final List<String> estados) {
        this.estados = String.join(",", estados);
    }
}
