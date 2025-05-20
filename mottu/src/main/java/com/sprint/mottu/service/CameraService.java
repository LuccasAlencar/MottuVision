package com.sprint.mottu.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Camera;
import com.sprint.mottu.repository.CameraRepository;



@Service
public class CameraService {

    private static final String CACHE_NAME = "cameras";

    private final CameraRepository repository;

    public CameraService(CameraRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista paginada de câmeras, filtrando por status ou localização.
     * Se ambos filtros forem vazios, retorna todas as câmeras.
     */
    @Cacheable(value = CACHE_NAME, 
               key = "{ #status != null ? #status : '', #localizacao != null ? #localizacao : '', "
                   + "#pageable.pageNumber, #pageable.pageSize, #pageable.sort }")
    public Page<Camera> listar(String status, String localizacao, Pageable pageable) {
        boolean hasStatus = status != null && !status.isBlank();
        boolean hasLocal = localizacao != null && !localizacao.isBlank();

        if (!hasStatus && !hasLocal) {
            return repository.findAll(pageable);
        }
        if (hasStatus) {
            return repository.findByStatusContainingIgnoreCase(status, pageable);
        }
        return repository.findByLocalizacaoContainingIgnoreCase(localizacao, pageable);
    }

    /**
     * Busca uma câmera por ID ou lança ResourceNotFoundException.
     */
    public Camera buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Câmera não encontrada com ID " + id));
    }
    


    /**
     * Cria uma nova câmera e limpa o cache de listagem.
     */
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public Camera criar(Camera camera) {
        return repository.save(camera);
    }

    /**
     * Atualiza uma câmera existente e limpa o cache.
     */
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public Camera atualizar(Long id, Camera dados) {
        Camera existente = buscarEntidadePorId(id);
        existente.setLocalizacao(dados.getLocalizacao());
        existente.setStatus(dados.getStatus());
        existente.setUltimaVerificacao(dados.getUltimaVerificacao());
        return repository.save(existente);
    }

    /**
     * Remove uma câmera pelo ID e limpa o cache.
     */
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Camera existente = buscarEntidadePorId(id);
        repository.delete(existente);
    }
}