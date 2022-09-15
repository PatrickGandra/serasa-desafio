package com.serasa.desafio.service;

import java.util.List;

import org.modelmapper.ModelMapper;
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

    public AfinidadeService(final AfinidadeRepository afinidadeRepository) {
        this.afinidadeRepository = afinidadeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<String> findByRegiao(final String regiao) {
        return afinidadeRepository.findByRegiao(regiao).getEstados();
    }

    @Transactional
    public ResponseEntity<AfinidadeDTO> create(final AfinidadeDTO afinidadeDTO) {
        Afinidade saved = null;

        try {
            saved = afinidadeRepository.save(modelMapper.map(afinidadeDTO, Afinidade.class));
        } catch (final Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(modelMapper.map(saved, AfinidadeDTO.class), HttpStatus.CREATED);
    }
}
