package com.sprint.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNome(String nome);
}