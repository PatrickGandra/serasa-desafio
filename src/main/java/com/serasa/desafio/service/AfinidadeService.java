package com.serasa.desafio.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serasa.desafio.dto.AfinidadeDTO;
import com.serasa.desafio.model.Afinidade;
import com.serasa.desafio.repository.AfinidadeRepository;

@Service
public class AfinidadeService {

    private final AfinidadeRepository afinidadeRepository;

    private final ModelMapper modelMapper;

    private final Logger log = LoggerFactory.getLogger(AfinidadeService.class);

    public AfinidadeService(final AfinidadeRepository afinidadeRepository) {
        this.afinidadeRepository = afinidadeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<String> findByRegiao(final String regiao) {
        return Optional.ofNullable(afinidadeRepository.findByRegiao(regiao))
                .map(Afinidade::getEstados)
                .orElse(new LinkedList<>());
    }

    @Transactional
    public ResponseEntity<AfinidadeDTO> create(final AfinidadeDTO afinidadeDTO) {
        Afinidade saved = null;

        try {
            saved = afinidadeRepository.save(modelMapper.map(afinidadeDTO, Afinidade.class));
        } catch (final Exception exception) {
            log.error("Ocorreu um erro ao salva a Afinidade:: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(modelMapper.map(saved, AfinidadeDTO.class), HttpStatus.CREATED);
    }
}
