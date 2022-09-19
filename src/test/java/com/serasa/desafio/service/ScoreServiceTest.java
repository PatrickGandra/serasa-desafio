package com.serasa.desafio.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.serasa.desafio.dto.ScoreDTO;
import com.serasa.desafio.model.Score;
import com.serasa.desafio.repository.ScoreRepository;
import com.serasa.desafio.util.ScoreCreator;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do service do score")
class ScoreServiceTest {

    @InjectMocks
    ScoreService scoreServiceMock;

    @Mock
    ScoreRepository scoreRepositoryMock;

    @Test
    @DisplayName("Retorna um objeto ScoreDTO quando sucesso")
    void create_RetornaScoreDTO_QuandoSucesso() {
        final Score score = ScoreCreator.createScore();

        when(scoreRepositoryMock.save(any(Score.class))).thenReturn(score);
        final ResponseEntity<ScoreDTO> scoreDTOResponseEntity = scoreServiceMock.create(ScoreCreator.createScoreDTO());
        final ScoreDTO scoreDTO = scoreDTOResponseEntity.getBody();
        final HttpStatus statusCode = scoreDTOResponseEntity.getStatusCode();

        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(scoreDTO).isNotNull();
        Assertions.assertThat(scoreDTO.getDescricao()).isEqualTo(score.getDescricao());
        Assertions.assertThat(scoreDTO.getInicial()).isEqualTo(score.getInicial());
        Assertions.assertThat(scoreDTO.getFim()).isEqualTo(score.getFim());
    }

    @Test
    @DisplayName("Retorna um Status code 500 em falha ao salvar um objeto ScoreDTO")
    void create_RetornaNull_QuandoFalha() {
        final ResponseEntity<ScoreDTO> scoreDTOResponseEntity = scoreServiceMock.create(null);

        Assertions.assertThat(scoreDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertThat(scoreDTOResponseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Retorna a Descricao do objeto Score quando sucesso")
    void getDescricao_RetornaDescricaoScore_QuandoSucesso() {
        when(scoreRepositoryMock.getDescricao(1000)).thenReturn("Recomendável");
        final String descricao = scoreServiceMock.getDescricao(1000);

        Assertions.assertThat(descricao)
                .isNotBlank()
                .isEqualTo("Recomendável");
    }

    @Test
    @DisplayName("Retorna a Descricao vazia quando falha")
    void getDescricao_RetornaVazio_QuandoFalha() {
        final String descricao = scoreServiceMock.getDescricao(2000);

        Assertions.assertThat(descricao)
                .isBlank()
                .isEqualTo("");
    }
}
