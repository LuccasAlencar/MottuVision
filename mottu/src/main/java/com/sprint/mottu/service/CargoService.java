package com.sprint.mottu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sprint.mottu.dto.CargoDTO;
import com.sprint.mottu.exception.ResourceNotFoundException;
import com.sprint.mottu.model.Cargo;
import com.sprint.mottu.repository.CargoRepository;

import jakarta.transaction.Transactional;

@Service
public class CargoService {

    private static final String CACHE_NAME = "cargos";

    @Autowired
    private CargoRepository cargoRepository;

    @Cacheable(value = CACHE_NAME)
    public List<CargoDTO> listarTodos() {
        return cargoRepository.findAll().stream()
            .map(c -> new CargoDTO(
                c.getId(), c.getNome(), c.getNivelPermissao(), c.getPermissoes()
            ))
            .collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public CargoDTO buscarPorId(Long id) {
        Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
        return new CargoDTO(
            cargo.getId(), cargo.getNome(), cargo.getNivelPermissao(), cargo.getPermissoes()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public CargoDTO criar(CargoDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());
        cargo.setNivelPermissao(dto.getNivelPermissao());
        cargo.setPermissoes(dto.getPermissoes());
        cargo = cargoRepository.save(cargo);
        return new CargoDTO(
            cargo.getId(), cargo.getNome(), cargo.getNivelPermissao(), cargo.getPermissoes()
        );
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public CargoDTO atualizar(Long id, CargoDTO dto) {
        Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));

        cargo.setNome(dto.getNome());
        cargo.setNivelPermissao(dto.getNivelPermissao());
        cargo.setPermissoes(dto.getPermissoes());
        cargo = cargoRepository.save(cargo);

        return new CargoDTO(
            cargo.getId(), cargo.getNome(), cargo.getNivelPermissao(), cargo.getPermissoes()
        );
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deletar(Long id) {
        Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
        cargoRepository.delete(cargo);
    }

    public Cargo buscarEntidadePorId(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
    }
}
