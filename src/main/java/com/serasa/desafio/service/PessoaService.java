package com.serasa.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.model.Pessoa;
import com.serasa.desafio.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final ModelMapper modelMapper;

    public PessoaService(final PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = new ModelMapper();
    }

    public ResponseEntity<List<PessoaDTO>> findAll() {
        final List<Pessoa> pessoaList = pessoaRepository.findAll();

        final List<PessoaDTO> pessoaDTOList = pessoaList.stream()
                .map(it -> modelMapper.map(it, PessoaDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(pessoaDTOList, HttpStatus.OK);
    }

    public ResponseEntity<PessoaDTO> findById(final Long id) {
        return pessoaRepository.findById(id)
                .map(it -> ResponseEntity.ok(modelMapper.map(it, PessoaDTO.class)))
                .orElse(ResponseEntity.noContent().build());
    }

    @Transactional
    public ResponseEntity<PessoaDTO> create(final PessoaDTO pessoa) {
        Pessoa saved = null;

        try {
            saved = pessoaRepository.save(modelMapper.map(pessoa, Pessoa.class));

        } catch (final Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(modelMapper.map(saved, PessoaDTO.class), HttpStatus.CREATED);
    }
}
