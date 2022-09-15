package com.serasa.desafio.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Afinidade {

    @Id
    private String regiao;
    private String estados;

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
