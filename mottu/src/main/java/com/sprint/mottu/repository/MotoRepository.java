package com.sprint.mottu.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.mottu.model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    /*Busca paginada por marca (contendo o texto, case‑insensitive).*/
    Page<Moto> findByMarcaContainingIgnoreCase(String marca, Pageable pageable);

    /*Busca paginada por modelo (contendo o texto, case‑insensitive).*/
    Page<Moto> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);
}