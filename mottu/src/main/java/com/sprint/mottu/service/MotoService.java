package com.sprint.mottu.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Moto;
import com.sprint.mottu.repository.MotoRepository;

@Service
public class MotoService {

    private static final String CACHE_NAME = "motos";

    private final MotoRepository repository;

    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

    // 🔍 Leituras com cache
    @Cacheable(value = CACHE_NAME, key = "{#marca, #modelo, #pageable.pageNumber, #pageable.pageSize, #pageable.sort}")
    public Page<Moto> listar(String marca, String modelo, Pageable pageable) {
        if ((marca == null || marca.isBlank()) && (modelo == null || modelo.isBlank())) {
            return repository.findAll(pageable);
        }
        if (marca != null && !marca.isBlank()) {
            return repository.findByMarcaContainingIgnoreCase(marca, pageable);
        }
        return repository.findByModeloContainingIgnoreCase(modelo, pageable);
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public Moto buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com ID " + id));
    }

    // ✏️ Escritas com invalidação de cache
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public Moto criar(Moto moto) {
        return repository.save(moto);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public Moto atualizar(Long id, Moto dadosAtualizados) {
        Moto motoExistente = buscarEntidadePorId(id);

        motoExistente.setPlaca(dadosAtualizados.getPlaca());
        motoExistente.setMarca(dadosAtualizados.getMarca());
        motoExistente.setModelo(dadosAtualizados.getModelo());
        motoExistente.setCor(dadosAtualizados.getCor());
        motoExistente.setPresente(dadosAtualizados.getPresente());

        return repository.save(motoExistente);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Moto motoExistente = buscarEntidadePorId(id);
        repository.delete(motoExistente);
    }
}
