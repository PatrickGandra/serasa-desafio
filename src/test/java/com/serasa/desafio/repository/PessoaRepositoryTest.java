package com.serasa.desafio.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.serasa.desafio.model.Pessoa;
import com.serasa.desafio.util.PessoaCreator;

@SpringBootTest
@DisplayName("teste para pessoa repository")
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Salva uma pessoa quando bem sucedido")
    void save_PersistePessoa_QuandoSuceso() {
        final Pessoa pessoaParaSalvar = PessoaCreator.createPessoa();

        final Pessoa pessoaSalva = pessoaRepository.save(pessoaParaSalvar);

        Assertions.assertThat(pessoaSalva).isNotNull();
        Assertions.assertThat(pessoaSalva.getId()).isNotNull();
        Assertions.assertThat(pessoaSalva.getNome()).isEqualTo(pessoaParaSalvar.getNome());
        Assertions.assertThat(pessoaSalva.getIdade()).isEqualTo(pessoaParaSalvar.getIdade());
        Assertions.assertThat(pessoaSalva.getIdade()).isEqualTo(pessoaParaSalvar.getIdade());
        Assertions.assertThat(pessoaSalva.getRegiao()).isEqualTo(pessoaParaSalvar.getRegiao());
        Assertions.assertThat(pessoaSalva.getScore()).isEqualTo(pessoaParaSalvar.getScore());
    }

    @Test
    @DisplayName("Busca uma pessoa quando bem sucedido")
    void findById_BuscaPessoa_QuandoSuceso() {
        final Pessoa pessoaSalva = pessoaRepository.save(PessoaCreator.createPessoa());
        final Pessoa pessoaEncontrada = pessoaRepository.findById(1L).orElse(null);

        Assertions.assertThat(pessoaSalva).isNotNull();
        Assertions.assertThat(pessoaSalva.getId()).isNotNull();
        Assertions.assertThat(pessoaSalva.getNome()).isEqualTo(pessoaEncontrada.getNome());
        Assertions.assertThat(pessoaSalva.getIdade()).isEqualTo(pessoaEncontrada.getIdade());
        Assertions.assertThat(pessoaSalva.getIdade()).isEqualTo(pessoaEncontrada.getIdade());
        Assertions.assertThat(pessoaSalva.getRegiao()).isEqualTo(pessoaEncontrada.getRegiao());
        Assertions.assertThat(pessoaSalva.getScore()).isEqualTo(pessoaEncontrada.getScore());
    }

    @Test
    @DisplayName("Busca uma pessoa quando bem sucedido")
    void findById_BuscaPessoaERetornaNull_QuandoErro() {
        final Pessoa pessoaEncontrada = pessoaRepository.findById(2L).orElse(null);

        Assertions.assertThat(pessoaEncontrada).isNull();
    }
}
