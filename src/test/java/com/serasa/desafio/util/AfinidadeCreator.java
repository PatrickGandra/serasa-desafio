package com.serasa.desafio.util;

import java.util.List;

import com.serasa.desafio.dto.AfinidadeDTO;
import com.serasa.desafio.model.Afinidade;

public final class AfinidadeCreator {

    private AfinidadeCreator() {
    }

    public static Afinidade createAfinidade() {
        return new Afinidade("sudeste", "MG,SP,RJ,ES");
    }

    public static AfinidadeDTO createAfinidadeDTO() {
        return new AfinidadeDTO("sudeste", List.of("MG", "SP", "RJ", "ES"));
    }

    public static List<String> getEstadosSudeste() {
        return List.of("MG", "SP", "RJ", "ES");
    }

    public static List<String> getEstadosSul() {
        return List.of("PR", "SC", "RS");
    }
}
