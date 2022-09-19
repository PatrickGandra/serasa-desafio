package com.serasa.desafio.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.serasa.desafio.model.Score;
import com.serasa.desafio.util.ScoreCreator;

@SpringBootTest
@DisplayName("teste para score repository")
class ScoreRepositoryTest {

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    @DisplayName("Salva um score quando bem sucedido")
    void save_PersisteScore_QuandoSuceso() {
        final Score scoreParaSalvar = ScoreCreator.createScore();

        final Score scoreSalvo = scoreRepository.save(scoreParaSalvar);

        Assertions.assertThat(scoreSalvo).isNotNull();
        Assertions.assertThat(scoreSalvo.getDescricao()).isEqualTo(scoreParaSalvar.getDescricao());
        Assertions.assertThat(scoreSalvo.getInicial()).isEqualTo(scoreParaSalvar.getInicial());
        Assertions.assertThat(scoreSalvo.getFim()).isEqualTo(scoreParaSalvar.getFim());
    }

    @Test
    @DisplayName("Retorna a descricao do score quando sucesso")
    void getDescricao_RetornaDescricao_QuandoSuceso() {
        scoreRepository.save(ScoreCreator.createScore());

        final String scoreDescricao = scoreRepository.getDescricao(200);

        Assertions.assertThat(scoreDescricao)
                .isNotNull()
                .isEqualToIgnoringCase("insuficiente");
    }
}
