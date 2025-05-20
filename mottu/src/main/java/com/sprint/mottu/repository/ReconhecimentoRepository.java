package com.sprint.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.Reconhecimento;

@Repository
public interface ReconhecimentoRepository extends JpaRepository<Reconhecimento, Long> {
    // ex: findByDataHoraBetween, findByCameraId
}