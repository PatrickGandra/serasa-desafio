package com.serasa.desafio.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serasa.desafio.dto.ScoreDTO;
import com.serasa.desafio.model.Score;
import com.serasa.desafio.repository.ScoreRepository;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    private final ModelMapper modelMapper;

    private final Logger log = LoggerFactory.getLogger(ScoreService.class);

    public ScoreService(final ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public ResponseEntity<ScoreDTO> create(final ScoreDTO scoreDTO) {
        Score saved = null;
        try {
            saved = scoreRepository.save(modelMapper.map(scoreDTO, Score.class));
        } catch (final Exception exception) {
            log.error("Ocorreu um erro ao salvar o Score:: {}", exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saved, ScoreDTO.class));
    }

    public String getDescricao(final int score) {
        return scoreRepository.getDescricao(score).orElse("");
    }
}
