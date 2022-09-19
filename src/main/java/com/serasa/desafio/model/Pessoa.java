package com.serasa.desafio.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "data_inclusao", updatable = false)
    private LocalDate dataInclusao;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "telefone", length = 32)
    private String telefone;
    @Column(name = "idade", nullable = false)
    private int idade;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado", length = 19)
    private String estado;
    @Column(name = "regiao", length = 12, nullable = false)
    private String regiao;
    @Column(name = "score", nullable = false)
    private int score;

    public Pessoa() {
    }

    public Pessoa(final Long id, final LocalDate dataInclusao, final String nome, final String telefone, final int idade, final String cidade, final String estado,
                  final String regiao, final int score) {
        this.id = id;
        this.dataInclusao = dataInclusao;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
        this.regiao = regiao;
        this.score = score;
    }

    @PrePersist
    private void prePersistFunction() {
        dataInclusao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(final LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(final int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(final String regiao) {
        this.regiao = regiao;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }
}
