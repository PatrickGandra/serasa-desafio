package com.serasa.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.desafio.model.Afinidade;

public interface AfinidadeRepository extends JpaRepository<Afinidade, String> {

    Afinidade findByRegiao(final String regiao);
}
