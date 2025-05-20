package com.sprint.mottu.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.mottu.dto.MotoDTO;
import com.sprint.mottu.model.Moto;
import com.sprint.mottu.service.MotoService;

import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    /**
     * GET /api/motos
     * Listagem paginada, com filtros opcionais de marca e modelo.
     * Ex.: /api/motos?marca=Yamaha&modelo=FZ&page=0&size=5&sort=placa,asc
     */
    @GetMapping
    public ResponseEntity<Page<MotoDTO>> listar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            Pageable pageable) {

        Page<Moto> page = service.listar(marca, modelo, pageable);
        Page<MotoDTO> dtoPage = page.map(this::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    /**
     * GET /api/motos/{id}
     * Busca moto por ID (404 se não existir).
     */
    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscar(@PathVariable Long id) {
        Moto moto = service.buscarEntidadePorId(id);
        return ResponseEntity.ok(toDTO(moto));
    }

    /**
     * POST /api/motos
     * Cria uma nova moto. Retorna 201 com Location no header.
     */
    @PostMapping
    public ResponseEntity<MotoDTO> criar(@Valid @RequestBody MotoDTO dto) {
        Moto entidade = toEntity(dto);
        Moto criada = service.criar(entidade);
        MotoDTO resposta = toDTO(criada);
        URI uri = URI.create("/api/motos/" + criada.getId());
        return ResponseEntity.created(uri).body(resposta);
    }

    /**
     * PUT /api/motos/{id}
     * Atualiza moto existente (404 se não existir).
     */
    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MotoDTO dto) {

        Moto dados = toEntity(dto);
        Moto atualizada = service.atualizar(id, dados);
        return ResponseEntity.ok(toDTO(atualizada));
    }

    /**
     * DELETE /api/motos/{id}
     * Exclui moto existente (404 se não existir).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // === MÉTODOS AUXILIARES DE MAPEAMENTO ===

    private MotoDTO toDTO(Moto m) {
        return new MotoDTO(
            m.getId(),
            m.getPlaca(),
            m.getMarca(),
            m.getModelo(),
            m.getCor(),
            m.getPresente(),
            m.getDataCadastro()
        );
    }

    private Moto toEntity(MotoDTO dto) {
        Moto m = new Moto();
        m.setPlaca(dto.getPlaca());
        m.setMarca(dto.getMarca());
        m.setModelo(dto.getModelo());
        m.setCor(dto.getCor());
        m.setPresente(dto.getPresente());
        // dataCadastro e id não são setados aqui (id vem do path, dataCadastro do @PrePersist)
        return m;
    }
}