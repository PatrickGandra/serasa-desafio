package com.serasa.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.serasa.desafio.dto.PessoaConsultaDTO;
import com.serasa.desafio.dto.PessoaDTO;
import com.serasa.desafio.model.Pessoa;
import com.serasa.desafio.repository.PessoaRepository;
import com.serasa.desafio.util.AfinidadeCreator;
import com.serasa.desafio.util.PessoaCreator;

@ExtendWith(MockitoExtension.class)
@DisplayName("teste para pessoa service")
class PessoaServiceTest {

    public static final String RECOMENDAVEL = "Recomendável";
    private final List<String> estadosSudeste = AfinidadeCreator.getEstadosSudeste();
    @InjectMocks
    PessoaService pessoaServiceMock;
    @Mock
    PessoaRepository pessoaRepositoryMock;
    @Mock
    AfinidadeService afinidadeServiceMock;
    @Mock
    ScoreService scoreServiceMock;

    @Test
    @DisplayName("Retorna um objeto PessoaDTO quando sucesso")
    void create_RetornaPessoaDTO_QuandoSucesso() {
        final Pessoa pessoa = PessoaCreator.createPessoa();

        when(pessoaRepositoryMock.save(any(Pessoa.class))).thenReturn(pessoa);

        final ResponseEntity<PessoaDTO> pessoaDTOResponseEntity = pessoaServiceMock.create(PessoaCreator.createPessoaDTO());
        final PessoaDTO pessoaDTO = pessoaDTOResponseEntity.getBody();
        final HttpStatus statusCode = pessoaDTOResponseEntity.getStatusCode();

        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(pessoaDTO).isNotNull();
        Assertions.assertThat(pessoaDTO.getId())
                .isNotNull()
                .isEqualTo(pessoa.getId());
        Assertions.assertThat(pessoaDTO.getNome()).isEqualTo(pessoa.getNome());
        Assertions.assertThat(pessoaDTO.getDataInclusao()).isEqualTo(pessoa.getDataInclusao());
        Assertions.assertThat(pessoaDTO.getIdade()).isEqualTo(pessoa.getIdade());
        Assertions.assertThat(pessoaDTO.getRegiao()).isEqualTo(pessoa.getRegiao());
        Assertions.assertThat(pessoaDTO.getRegiao()).isEqualTo(pessoa.getRegiao());
        Assertions.assertThat(pessoaDTO.getScore()).isEqualTo(pessoa.getScore());
    }

    @Test
    @DisplayName("Retorna um Status code 500 em falha ao salvar um objeto PessoaDTO")
    void create_RetornaNull_QuandoFalha() {
        final ResponseEntity<PessoaDTO> pessoaDTOResponseEntity = pessoaServiceMock.create(null);

        Assertions.assertThat(pessoaDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertThat(pessoaDTOResponseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Retorna um objeto PessoaConsultaDTO pelo ID quando sucesso")
    void findById_RetornaPessoaConsultaDTO_QuandoSucesso() {
        final Pessoa pessoa = PessoaCreator.createPessoa();

        when(scoreServiceMock.getDescricao(pessoa.getScore())).thenReturn(RECOMENDAVEL);
        when(afinidadeServiceMock.findByRegiao(pessoa.getRegiao())).thenReturn(estadosSudeste);
        when(pessoaRepositoryMock.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));

        final ResponseEntity<PessoaConsultaDTO> pessoaConsultaDTOResponseEntity = pessoaServiceMock.findById(1L);
        final PessoaConsultaDTO pessoaConsultaDTO = pessoaConsultaDTOResponseEntity.getBody();
        final HttpStatus statusCode = pessoaConsultaDTOResponseEntity.getStatusCode();

        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(pessoaConsultaDTO).isNotNull();
        Assertions.assertThat(pessoaConsultaDTO.getNome()).isEqualTo(pessoa.getNome());
        Assertions.assertThat(pessoaConsultaDTO.getCidade()).isEqualTo(pessoa.getCidade());
        Assertions.assertThat(pessoaConsultaDTO.getTelefone()).isEqualTo(pessoa.getTelefone());
        Assertions.assertThat(pessoaConsultaDTO.getIdade()).isEqualTo(pessoa.getIdade());
        Assertions.assertThat(pessoaConsultaDTO.getScoreDescricao()).isEqualTo(RECOMENDAVEL);
        Assertions.assertThat(pessoaConsultaDTO.getEstados()).isEqualTo(List.of("MG", "SP", "RJ", "ES"));
    }

    @Test
    @DisplayName("Retorna um Status code 204 em falha ao consultar uma Pessoa")
    void findByID_RetornaNull_QuandoFalha() {
        final ResponseEntity<PessoaConsultaDTO> pessoaDTOResponseEntity = pessoaServiceMock.findById(null);

        Assertions.assertThat(pessoaDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(pessoaDTOResponseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Retorna Lista de Pessoas quando sucesso")
    void findAll_RetornaListaPessoa_QuandoSucesso() {
        when(scoreServiceMock.getDescricao(anyInt())).thenReturn(RECOMENDAVEL);
        when(afinidadeServiceMock.findByRegiao(anyString())).thenReturn(estadosSudeste);
        final List<Pessoa> listPessoa = PessoaCreator.createListPessoa();
        when(pessoaRepositoryMock.findAll()).thenReturn(listPessoa);
        final ResponseEntity<List<PessoaConsultaDTO>> listPessoaDTOResponseEntity = pessoaServiceMock.findAll();

        final List<PessoaConsultaDTO> listPessoaConsultaDTO = listPessoaDTOResponseEntity.getBody();
        final HttpStatus statusCode = listPessoaDTOResponseEntity.getStatusCode();

        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(listPessoaConsultaDTO).isNotEmpty().hasSize(2);

        for (int index = 0; index < listPessoaConsultaDTO.size(); index++) {

            Assertions.assertThat(listPessoaConsultaDTO.get(index).getNome()).isEqualTo(listPessoa.get(index).getNome());
            Assertions.assertThat(listPessoaConsultaDTO.get(index).getCidade()).isEqualTo(listPessoa.get(index).getCidade());
            Assertions.assertThat(listPessoaConsultaDTO.get(index).getTelefone()).isEqualTo(listPessoa.get(index).getTelefone());
            Assertions.assertThat(listPessoaConsultaDTO.get(index).getIdade()).isEqualTo(listPessoa.get(index).getIdade());
            Assertions.assertThat(listPessoaConsultaDTO.get(index).getScoreDescricao()).isEqualTo(RECOMENDAVEL);
            Assertions.assertThat(listPessoaConsultaDTO.get(index).getEstados()).isEqualTo(estadosSudeste);
        }
    }

    @Test
    @DisplayName("Retorna um Status code 204 em falha ao consultar uma lista Pessoa")
    void findAll_RetornaNull_QuandoFalha() {
        final ResponseEntity<List<PessoaConsultaDTO>> listPessoaDTOResponseEntity = pessoaServiceMock.findAll();

        Assertions.assertThat(listPessoaDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(listPessoaDTOResponseEntity.getBody()).isNull();
    }
}
