package com.serasa.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.serasa.desafio.dto.AfinidadeDTO;
import com.serasa.desafio.model.Afinidade;
import com.serasa.desafio.repository.AfinidadeRepository;
import com.serasa.desafio.util.AfinidadeCreator;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do service de afinidade")
class AfinidadeServiceTest {

    @InjectMocks
    AfinidadeService afinidadeServiceMock;

    @Mock
    AfinidadeRepository afinidadeRepositoryMock;

    @Test
    @DisplayName("Retorna um objeto AfinidadeDTO quando sucesso")
    void create_RetornaAfinidadeDTO_QuandoSucesso() {
        final Afinidade afinidade = AfinidadeCreator.createAfinidade();

        when(afinidadeRepositoryMock.save(any(Afinidade.class))).thenReturn(afinidade);
        final ResponseEntity<AfinidadeDTO>
                afinidadeDTOResponseEntity =
                afinidadeServiceMock.create(AfinidadeCreator.createAfinidadeDTO());
        final AfinidadeDTO afinidadeDTO = afinidadeDTOResponseEntity.getBody();
        final HttpStatus statusCode = afinidadeDTOResponseEntity.getStatusCode();

        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(afinidadeDTO).isNotNull();
        Assertions.assertThat(afinidadeDTO.getRegiao()).isEqualTo(afinidade.getRegiao());
        Assertions.assertThat(afinidadeDTO.getEstados()).isNotEmpty();
        Assertions.assertThat(afinidadeDTO.getEstados()).isEqualTo(afinidade.getEstados());
    }

    @Test
    @DisplayName("Retorna um Status code 500 em falha ao salvar um objeto AfinidadeDTO")
    void create_RetornaNull_QuandoFalha() {
        final ResponseEntity<AfinidadeDTO> afinidadeDTOResponseEntity = afinidadeServiceMock.create(null);

        Assertions.assertThat(afinidadeDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertThat(afinidadeDTOResponseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Retorna uma lista vazia quando nao encontra registro para a regiao informada")
    void findByRegiao_RetornaListaVazia_QuandoFalha() {
        final List<String> regiao = afinidadeServiceMock.findByRegiao(null);

        Assertions.assertThat(regiao).isEmpty();
    }

    @Test
    @DisplayName("Retorna uma lista de estados para uma regiao do pais quando sucesso")
    void findByRegiao_RetornaListaEstado_QuandoSucesso() {
        when(afinidadeRepositoryMock.findByRegiao("sudeste").orElse(null)).thenReturn(AfinidadeCreator.createAfinidade());
        final List<String> regiao = afinidadeServiceMock.findByRegiao("sudeste");

        Assertions.assertThat(regiao)
                .isNotEmpty()
                .hasSize(4)
                .isEqualTo(AfinidadeCreator.getEstadosSudeste());
    }

    @Test
    @DisplayName("Retorna uma lista de estados para uma regiao sul do pais quando sucesso")
    void findByRegiao_RetornaListaEstadoSul_QuandoSucesso() {
    when(afinidadeRepositoryMock.findByRegiao("sul").orElse(null)).thenReturn(new Afinidade("sul", "PR,SC,RS"));
        final List<String> regiao = afinidadeServiceMock.findByRegiao("sul");

        Assertions.assertThat(regiao)
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(AfinidadeCreator.getEstadosSul());
    }

}
