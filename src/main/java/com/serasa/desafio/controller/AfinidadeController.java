package com.serasa.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.AfinidadeDTO;
import com.serasa.desafio.service.AfinidadeService;

@RestController
@RequestMapping("/afinidade")
public class AfinidadeController {

    private final AfinidadeService afinidadeService;

    public AfinidadeController(final AfinidadeService afinidadeService) {
        this.afinidadeService = afinidadeService;
    }

    @PostMapping
    public ResponseEntity<AfinidadeDTO> create(@RequestBody final AfinidadeDTO afinidadeDTO) {
        return afinidadeService.create(afinidadeDTO);
    }
}

