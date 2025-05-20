package com.sprint.mottu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.dto.RegistroDTO;
import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Camera;
import com.sprint.mottu.model.Moto;
import com.sprint.mottu.model.Registro;
import com.sprint.mottu.repository.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistroService {

    private static final String CACHE_NAME = "registros";

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private MotoService motoService;

    @Cacheable(value = CACHE_NAME)
    public List<RegistroDTO> listarTodos() {
        return registroRepository.findAll().stream()
            .map(r -> new RegistroDTO(
                r.getId(),
                r.getDataHora(),
                r.getCamera().getId(),
                r.getMoto().getId(),
                r.getTipoAcao()
            ))
            .collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public RegistroDTO buscarPorId(Long id) {
        Registro registro = registroRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com ID: " + id));
        return new RegistroDTO(
            registro.getId(),
            registro.getDataHora(),
            registro.getCamera().getId(),
            registro.getMoto().getId(),
            registro.getTipoAcao()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public RegistroDTO criar(RegistroDTO dto) {
        Camera camera = cameraService.buscarEntidadePorId(dto.getIdCamera());
        Moto moto = motoService.buscarEntidadePorId(dto.getIdMoto());

        Registro registro = new Registro();
        registro.setDataHora(dto.getDataHora());
        registro.setCamera(camera);
        registro.setMoto(moto);
        registro.setTipoAcao(dto.getTipoAcao());

        registro = registroRepository.save(registro);
        return new RegistroDTO(
            registro.getId(),
            registro.getDataHora(),
            registro.getCamera().getId(),
            registro.getMoto().getId(),
            registro.getTipoAcao()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public RegistroDTO atualizar(Long id, RegistroDTO dto) {
        Registro registro = registroRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com ID: " + id));

        Camera camera = cameraService.buscarEntidadePorId(dto.getIdCamera());
        Moto moto = motoService.buscarEntidadePorId(dto.getIdMoto());

        registro.setDataHora(dto.getDataHora());
        registro.setCamera(camera);
        registro.setMoto(moto);
        registro.setTipoAcao(dto.getTipoAcao());

        registro = registroRepository.save(registro);
        return new RegistroDTO(
            registro.getId(),
            registro.getDataHora(),
            registro.getCamera().getId(),
            registro.getMoto().getId(),
            registro.getTipoAcao()
        );
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Registro registro = registroRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com ID: " + id));
        registroRepository.delete(registro);
    }
}
