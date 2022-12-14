package com.serasa.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.AfinidadeDTO;
import com.serasa.desafio.service.AfinidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/afinidade")
@Api(value = "/afinidade", tags = "afinidade", description = "gestão de afinidades")
public class AfinidadeController {

    private final AfinidadeService afinidadeService;

    public AfinidadeController(final AfinidadeService afinidadeService) {
        this.afinidadeService = afinidadeService;
    }

    @ApiOperation(value = "cria um objeto afinidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "objeto criado com sucesso"),
            @ApiResponse(code = 500, message = "erro interno da aplicação")
    })
    @PostMapping
    public ResponseEntity<AfinidadeDTO> create(@RequestBody final AfinidadeDTO afinidadeDTO) {
        return afinidadeService.create(afinidadeDTO);
    }
}

