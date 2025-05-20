package com.sprint.mottu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CameraDTO {

    private Long id;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 100, message = "Localização deve ter no máximo 100 caracteres")
    private String localizacao;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    private String status;

    // Data/hora da última verificação; opcional no envio, mas retornado para o cliente
    private LocalDateTime ultimaVerificacao;

    public CameraDTO() {
    }

    public CameraDTO(Long id, String localizacao, String status, LocalDateTime ultimaVerificacao) {
        this.id = id;
        this.localizacao = localizacao;
        this.status = status;
        this.ultimaVerificacao = ultimaVerificacao;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUltimaVerificacao() {
        return ultimaVerificacao;
    }

    public void setUltimaVerificacao(LocalDateTime ultimaVerificacao) {
        this.ultimaVerificacao = ultimaVerificacao;
    }
}