package com.serasa.desafio.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.serasa.desafio.model.Afinidade;
import com.serasa.desafio.util.AfinidadeCreator;

@SpringBootTest
@DisplayName("teste para afinidade repository")
class AfinidadeRepositoryTest {

    public static final String SUDESTE = "sudeste";
    @Autowired
    private AfinidadeRepository afinidadeRepository;

    @Test
    @DisplayName("Salva uma afinidade quando bem sucedido")
    void save_PersisteAfinidade_QuandoSuceso() {
        final Afinidade afinidadeParaSalvar = AfinidadeCreator.createAfinidade();

        final Afinidade afinidadeSalva = afinidadeRepository.save(afinidadeParaSalvar);

        Assertions.assertThat(afinidadeSalva).isNotNull();

        Assertions.assertThat(afinidadeSalva.getRegiao()).isNotNull();

        Assertions.assertThat(afinidadeSalva.getRegiao()).isEqualTo(afinidadeParaSalvar.getRegiao());
    }

    @Test
    @DisplayName("Busca por regiao um objeto Afinidade")
    void findByRegiao_RetornaAfinidade_QuandoSucesso() {
        afinidadeRepository.save(AfinidadeCreator.createAfinidade());
        final Afinidade afinidadeEncontrada = afinidadeRepository.findByRegiao(SUDESTE);

        Assertions.assertThat(afinidadeEncontrada).isNotNull();

        Assertions.assertThat(afinidadeEncontrada.getRegiao())
                .isNotNull()
                .isEqualTo(SUDESTE);

        Assertions.assertThat(afinidadeEncontrada.getEstados()).isEqualTo(AfinidadeCreator.getEstadosSudeste());
    }

    @Test
    @DisplayName("Busca por regiao um objeto Afinidade e retorna null quando nao encontrado")
    void findByRegiao_RetornaNull_QuandoNaoEncontrado() {
        final Afinidade afinidadeEncontrada = afinidadeRepository.findByRegiao(null);

        Assertions.assertThat(afinidadeEncontrada).isNull();
    }
}
