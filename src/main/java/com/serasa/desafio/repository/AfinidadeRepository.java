package com.serasa.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serasa.desafio.model.Afinidade;

@Repository
public interface AfinidadeRepository extends JpaRepository<Afinidade, String> {

    Afinidade findByRegiao(final String regiao);
}
