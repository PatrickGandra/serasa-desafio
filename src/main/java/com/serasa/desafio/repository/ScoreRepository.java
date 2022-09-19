package com.serasa.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serasa.desafio.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, String> {

    @Query(value = "SELECT s.descricao FROM score AS s WHERE :score >= s.inicial AND :score <= s.fim", nativeQuery = true)
    String getDescricao(@Param("score") final int score);
}
