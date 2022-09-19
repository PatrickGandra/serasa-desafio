package com.serasa.desafio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.PessoaConsultaDTO;
import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoa")
@Api(value = "/pessoa", tags = "pessoas", description = "gestão de pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(final PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ApiOperation(value = "Cria um Objeto pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "objeto criado com sucesso"),
            @ApiResponse(code = 500, message = "erro interno da aplicação")
    })
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody final PessoaDTO pessoaDTO) {
        return pessoaService.create(pessoaDTO);
    }

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "requisição executada com sucesso"),
            @ApiResponse(code = 204, message = "nenhum registro encontrado")
    })
    @GetMapping
    public ResponseEntity<List<PessoaConsultaDTO>> findAll() {
        return pessoaService.findAll();
    }

    @ApiOperation(value = "Retorna um objeto pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "requisição executada com sucesso"),
            @ApiResponse(code = 204, message = "nenhum registro encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaConsultaDTO> findById(@PathVariable final Long id) {
        return pessoaService.findById(id);
    }
}
