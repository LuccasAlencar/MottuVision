package com.sprint.mottu.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.mottu.dto.LogAlteracoesDTO;
import com.sprint.mottu.service.LogAlteracoesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/log-alteracoes")
public class LogAlteracoesController {

    @Autowired
    private LogAlteracoesService service;

    @GetMapping
    public ResponseEntity<List<LogAlteracoesDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogAlteracoesDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LogAlteracoesDTO> criar(@Valid @RequestBody LogAlteracoesDTO dto) {
        LogAlteracoesDTO criado = service.criar(dto);
        URI uri = URI.create("/api/log-alteracoes/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}