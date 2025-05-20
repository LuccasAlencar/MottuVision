package com.sprint.mottu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    /**
     * Busca câmeras cujo status contenha o texto (case‑insensitive).
     */
    Page<Camera> findByStatusContainingIgnoreCase(String status, Pageable pageable);

    /**
     * Busca câmeras pela localização (contendo o texto, case‑insensitive).
     */
    Page<Camera> findByLocalizacaoContainingIgnoreCase(String localizacao, Pageable pageable);
}