package com.serasa.desafio.util;

import java.time.LocalDate;
import java.util.List;

import com.serasa.desafio.dto.PessoaConsultaDTO;
import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.model.Pessoa;

public final class PessoaCreator {

    private PessoaCreator() {

    }

    public static Pessoa createPessoa() {
        return new Pessoa(1L, LocalDate.now(), "Patrick", "(32) 98877-1757", 31, "Juiz de Fora",
                          "Minas Gerais", "sudeste", 1000);
    }

    public static PessoaDTO createPessoaDTO() {
        return new PessoaDTO(1L, LocalDate.now(), "Patrick", "(32) 98877-1757", 31, "Juiz de Fora",
                             "Minas Gerais", "sudeste", 1000);
    }

    public static PessoaConsultaDTO createPessoaConsultaDTO() {
        return new PessoaConsultaDTO("Patrick", "Juiz de Fora", "(32) 98877-1757", 31,
                                     "Recomendável", List.of("MG", "SP", "RJ", "ES"));
    }

    public static List<Pessoa> createListPessoa() {
        final Pessoa pessoa1 = new Pessoa(2L, LocalDate.now(), "José", "(21) 98841-9637", 45, "Rio de Janeiro",
                                          "Rio de Janeiro", "sudeste", 1000);
        final Pessoa pessoa2 = new Pessoa(3L, LocalDate.now(), "Maria", "(28) 98823-7452", 23, "Vitória",
                                          "Vitória", "sudeste", 1000);

        return List.of(pessoa1, pessoa2);
    }
}
