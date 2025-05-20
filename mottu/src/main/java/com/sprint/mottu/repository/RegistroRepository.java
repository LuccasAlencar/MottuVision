package com.sprint.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    // Métodos customizados podem ser adicionados aqui, ex. findByDataHoraBetween
}