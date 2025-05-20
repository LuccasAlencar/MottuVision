package com.sprint.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.LogAlteracoes;

@Repository
public interface LogAlteracoesRepository extends JpaRepository<LogAlteracoes, Long> {
    // ex: findByDataHoraBetween, findByUsuarioId
}