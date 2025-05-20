package com.sprint.mottu.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.mottu.dto.ReconhecimentoDTO;
import com.sprint.mottu.service.ReconhecimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reconhecimentos")
public class ReconhecimentoController {

    @Autowired
    private ReconhecimentoService service;

    @GetMapping
    public ResponseEntity<List<ReconhecimentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReconhecimentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReconhecimentoDTO> criar(@Valid @RequestBody ReconhecimentoDTO dto) {
        ReconhecimentoDTO criado = service.criar(dto);
        URI uri = URI.create("/api/reconhecimentos/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReconhecimentoDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReconhecimentoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}