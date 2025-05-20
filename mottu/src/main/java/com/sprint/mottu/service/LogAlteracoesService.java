package com.sprint.mottu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.dto.LogAlteracoesDTO;
import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.LogAlteracoes;
import com.sprint.mottu.model.Moto;
import com.sprint.mottu.model.Usuario;
import com.sprint.mottu.repository.LogAlteracoesRepository;

import jakarta.transaction.Transactional;

@Service
public class LogAlteracoesService {

    private static final String CACHE_NAME = "logAlteracoes";

    @Autowired
    private LogAlteracoesRepository repo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MotoService motoService;

    @Cacheable(value = CACHE_NAME)
    public List<LogAlteracoesDTO> listarTodos() {
        return repo.findAll().stream().map(l -> new LogAlteracoesDTO(
            l.getId(), l.getDataHora(), l.getUsuario().getId(), l.getMoto().getId(),
            l.getCampoAlterado(), l.getValorAntigo(), l.getValorNovo()
        )).collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public LogAlteracoesDTO buscarPorId(Long id) {
        LogAlteracoes l = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Log não encontrado com ID: " + id));
        return new LogAlteracoesDTO(
            l.getId(), l.getDataHora(), l.getUsuario().getId(), l.getMoto().getId(),
            l.getCampoAlterado(), l.getValorAntigo(), l.getValorNovo()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public LogAlteracoesDTO criar(LogAlteracoesDTO dto) {
        Usuario u = usuarioService.buscarEntidadePorId(dto.getIdUsuario());
        Moto m = motoService.buscarEntidadePorId(dto.getIdMoto());

        LogAlteracoes l = new LogAlteracoes();
        l.setDataHora(dto.getDataHora());
        l.setUsuario(u);
        l.setMoto(m);
        l.setCampoAlterado(dto.getCampoAlterado());
        l.setValorAntigo(dto.getValorAntigo());
        l.setValorNovo(dto.getValorNovo());

        l = repo.save(l);
        return new LogAlteracoesDTO(
            l.getId(), l.getDataHora(), l.getUsuario().getId(), l.getMoto().getId(),
            l.getCampoAlterado(), l.getValorAntigo(), l.getValorNovo()
        );
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        LogAlteracoes l = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Log não encontrado com ID: " + id));
        repo.delete(l);
    }
}
