package com.serasa.desafio.dto;

import java.util.List;

public class AfinidadeDTO {

    private String regiao;
    private List<String> estados;

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(final String regiao) {
        this.regiao = regiao;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(final List<String> estados) {
        this.estados = estados;
    }
}
