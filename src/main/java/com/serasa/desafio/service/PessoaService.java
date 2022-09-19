package com.serasa.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serasa.desafio.dto.PessoaConsultaDTO;
import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.model.Pessoa;
import com.serasa.desafio.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final AfinidadeService afinidadeService;

    private final ScoreService scoreService;

    private final ModelMapper modelMapper;

    private final Logger log = LoggerFactory.getLogger(PessoaService.class);

    public PessoaService(final PessoaRepository pessoaRepository, final AfinidadeService afinidadeService, final ScoreService scoreService) {
        this.pessoaRepository = pessoaRepository;
        this.afinidadeService = afinidadeService;
        this.scoreService = scoreService;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public ResponseEntity<PessoaDTO> create(final PessoaDTO pessoa) {
        Pessoa saved = null;

        try {
            saved = pessoaRepository.save(modelMapper.map(pessoa, Pessoa.class));

        } catch (final Exception exception) {
            log.error("Ocorreu um erro ao salva a Pessoa:: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(modelMapper.map(saved, PessoaDTO.class), HttpStatus.CREATED);
    }

    public ResponseEntity<List<PessoaConsultaDTO>> findAll() {
        final List<Pessoa> pessoaList = pessoaRepository.findAll();

        if (pessoaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        final List<PessoaConsultaDTO> pessoaConsultaDTOList = pessoaList.stream()
                .map(this::mapPessoaConsulta)
                .collect(Collectors.toList());

        return new ResponseEntity<>(pessoaConsultaDTOList, HttpStatus.OK);
    }

    public ResponseEntity<PessoaConsultaDTO> findById(final Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> ResponseEntity.ok(mapPessoaConsulta(pessoa)))
                .orElse(ResponseEntity.noContent().build());
    }

    private PessoaConsultaDTO mapPessoaConsulta(final Pessoa pessoa) {
        final List<String> estados = afinidadeService.findByRegiao(pessoa.getRegiao());
        final String descricao = scoreService.getDescricao(pessoa.getScore());

        final PessoaConsultaDTO pessoaConsultaDTO = modelMapper.map(pessoa, PessoaConsultaDTO.class);
        pessoaConsultaDTO.setEstados(estados);
        pessoaConsultaDTO.setScoreDescricao(descricao);

        return pessoaConsultaDTO;
    }
}
