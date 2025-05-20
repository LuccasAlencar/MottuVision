package com.sprint.mottu.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.mottu.dto.CameraDTO;
import com.sprint.mottu.model.Camera;
import com.sprint.mottu.service.CameraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    private final CameraService service;

    public CameraController(CameraService service) {
        this.service = service;
    }

    /**
     * GET /api/cameras
     * Listagem paginada com filtros opcionais de status e localização.
     */
    @GetMapping
    public ResponseEntity<Page<CameraDTO>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String localizacao,
            Pageable pageable) {

        Page<Camera> page = service.listar(status, localizacao, pageable);
        Page<CameraDTO> dtoPage = page.map(this::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    /**
     * GET /api/cameras/{id}
     * Busca câmera por ID (404 se não existir).
     */
    @GetMapping("/{id}")
    public ResponseEntity<CameraDTO> buscar(@PathVariable Long id) {
        Camera cam = service.buscarEntidadePorId(id);
        return ResponseEntity.ok(toDTO(cam));
    }

    /**
     * POST /api/cameras
     * Cria nova câmera. Retorna 201 e header Location.
     */
    @PostMapping
    public ResponseEntity<CameraDTO> criar(@Valid @RequestBody CameraDTO dto) {
        Camera entidade = toEntity(dto);
        Camera criada = service.criar(entidade);
        URI uri = URI.create("/api/cameras/" + criada.getId());
        return ResponseEntity.created(uri).body(toDTO(criada));
    }

    /**
     * PUT /api/cameras/{id}
     * Atualiza câmera existente (404 se não existir).
     */
    @PutMapping("/{id}")
    public ResponseEntity<CameraDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CameraDTO dto) {

        Camera dados = toEntity(dto);
        Camera atualizada = service.atualizar(id, dados);
        return ResponseEntity.ok(toDTO(atualizada));
    }

    /**
     * DELETE /api/cameras/{id}
     * Remove câmera existente (404 se não existir).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // === MÉTODOS DE MAPEAMENTO ===

    private CameraDTO toDTO(Camera c) {
        return new CameraDTO(
                c.getId(),
                c.getLocalizacao(),
                c.getStatus(),
                c.getUltimaVerificacao()
        );
    }

    private Camera toEntity(CameraDTO dto) {
        Camera c = new Camera();
        c.setLocalizacao(dto.getLocalizacao());
        c.setStatus(dto.getStatus());
        c.setUltimaVerificacao(dto.getUltimaVerificacao());
        return c;
    }
}