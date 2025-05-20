package com.sprint.mottu.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotoDTO {

    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    @Size(max = 7, message = "Placa deve ter no máximo 7 caracteres")
    private String placa;

    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 50, message = "Modelo deve ter no máximo 50 caracteres")
    private String modelo;

    @Size(max = 20, message = "Cor deve ter no máximo 20 caracteres")
    private String cor;

    @NotNull(message = "Presença deve ser informada")
    private Boolean presente;

    private Instant dataCadastro;

    // Construtor vazio (necessário para deserialização)
    public MotoDTO() {
    }

    // Construtor completo (útil para mapear entidade → DTO)
    public MotoDTO(Long id, String placa, String marca, String modelo,
                   String cor, Boolean presente, Instant dataCadastro) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.presente = presente;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Boolean getPresente() { return presente; }
    public void setPresente(Boolean presente) { this.presente = presente; }

    public Instant getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Instant dataCadastro) { this.dataCadastro = dataCadastro; }
}