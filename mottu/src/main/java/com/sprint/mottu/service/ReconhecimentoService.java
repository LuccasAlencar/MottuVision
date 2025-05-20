package com.sprint.mottu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.dto.ReconhecimentoDTO;
import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Camera;
import com.sprint.mottu.model.Moto;
import com.sprint.mottu.model.Reconhecimento;
import com.sprint.mottu.repository.ReconhecimentoRepository;

import jakarta.transaction.Transactional;

@Service
public class ReconhecimentoService {

    private static final String CACHE_NAME = "reconhecimentos";

    @Autowired
    private ReconhecimentoRepository repo;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private MotoService motoService;

    @Cacheable(value = CACHE_NAME)
    public List<ReconhecimentoDTO> listarTodos() {
        return repo.findAll().stream().map(r -> new ReconhecimentoDTO(
            r.getId(), r.getDataHora(), r.getPrecisao(),
            r.getImagemCapturada(), r.getConfiancaMinima(),
            r.getCamera().getId(), r.getMoto().getId()
        )).collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public ReconhecimentoDTO buscarPorId(Long id) {
        Reconhecimento r = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reconhecimento não encontrado com ID: " + id));
        return new ReconhecimentoDTO(
            r.getId(), r.getDataHora(), r.getPrecisao(),
            r.getImagemCapturada(), r.getConfiancaMinima(),
            r.getCamera().getId(), r.getMoto().getId()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ReconhecimentoDTO criar(ReconhecimentoDTO dto) {
        Camera cam = cameraService.buscarEntidadePorId(dto.getIdCamera());
        Moto moto = motoService.buscarEntidadePorId(dto.getIdMoto());

        Reconhecimento r = new Reconhecimento();
        r.setDataHora(dto.getDataHora());
        r.setPrecisao(dto.getPrecisao());
        r.setImagemCapturada(dto.getImagemCapturada());
        r.setConfiancaMinima(dto.getConfiancaMinima());
        r.setCamera(cam);
        r.setMoto(moto);

        r = repo.save(r);
        return new ReconhecimentoDTO(
            r.getId(), r.getDataHora(), r.getPrecisao(),
            r.getImagemCapturada(), r.getConfiancaMinima(),
            r.getCamera().getId(), r.getMoto().getId()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ReconhecimentoDTO atualizar(Long id, ReconhecimentoDTO dto) {
        Reconhecimento r = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reconhecimento não encontrado com ID: " + id));

        Camera cam = cameraService.buscarEntidadePorId(dto.getIdCamera());
        Moto moto = motoService.buscarEntidadePorId(dto.getIdMoto());

        r.setDataHora(dto.getDataHora());
        r.setPrecisao(dto.getPrecisao());
        r.setImagemCapturada(dto.getImagemCapturada());
        r.setConfiancaMinima(dto.getConfiancaMinima());
        r.setCamera(cam);
        r.setMoto(moto);

        r = repo.save(r);
        return new ReconhecimentoDTO(
            r.getId(), r.getDataHora(), r.getPrecisao(),
            r.getImagemCapturada(), r.getConfiancaMinima(),
            r.getCamera().getId(), r.getMoto().getId()
        );
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Reconhecimento r = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reconhecimento não encontrado com ID: " + id));
        repo.delete(r);
    }
}
