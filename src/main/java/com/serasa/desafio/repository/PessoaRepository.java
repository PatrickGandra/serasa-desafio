package com.serasa.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.desafio.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
