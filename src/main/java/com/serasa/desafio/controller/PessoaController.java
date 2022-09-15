package com.serasa.desafio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(final PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody final PessoaDTO pessoaDTO) {
        return pessoaService.create(pessoaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable final Long id) {
        return pessoaService.findById(id);
    }
}
