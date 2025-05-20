package com.sprint.mottu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.dto.UsuarioDTO;
import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Cargo;
import com.sprint.mottu.model.Usuario;
import com.sprint.mottu.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private static final String CACHE_NAME = "usuarios";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoService cargoService;

    @Cacheable(value = CACHE_NAME)
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
            .map(UsuarioDTO::new)
            .collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = "'usuarioDTO:' + #id")
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return new UsuarioDTO(usuario);
    }

    @Cacheable(value = CACHE_NAME, key = "'entidade:' + #id")
    public Usuario buscarEntidadePorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public UsuarioDTO criar(UsuarioDTO dto) {
        Cargo cargo = cargoService.buscarEntidadePorId(dto.getIdCargo());
        Usuario usuario = new Usuario(dto, cargo);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        Cargo cargo = cargoService.buscarEntidadePorId(dto.getIdCargo());

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCargo(cargo);
        usuario.setAtivo(dto.getAtivo());

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        usuarioRepository.delete(usuario);
    }
}
