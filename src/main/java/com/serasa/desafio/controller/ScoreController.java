package com.serasa.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.ScoreDTO;
import com.serasa.desafio.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/score")
@Api(value = "/score", tags = "score", description = "gestão de score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(final ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @ApiOperation(value = "cria um objeto score")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "objeto criado com sucesso"),
            @ApiResponse(code = 500, message = "erro interno da aplicação")
    })
    @PostMapping
    public ResponseEntity<ScoreDTO> create(@RequestBody final ScoreDTO scoreDTO) {
        return scoreService.create(scoreDTO);
    }
}
