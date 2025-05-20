package com.sprint.mottu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistroDTO {

    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "ID da câmera é obrigatório")
    private Long idCamera;

    @NotNull(message = "ID da moto é obrigatório")
    private Long idMoto;

    @Size(max = 10, message = "Tipo de ação deve ter no máximo 10 caracteres")
    private String tipoAcao;

    public RegistroDTO() {}

    public RegistroDTO(Long id, LocalDateTime dataHora, Long idCamera, Long idMoto, String tipoAcao) {
        this.id = id;
        this.dataHora = dataHora;
        this.idCamera = idCamera;
        this.idMoto = idMoto;
        this.tipoAcao = tipoAcao;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Long getIdCamera() {
		return idCamera;
	}

	public void setIdCamera(Long idCamera) {
		this.idCamera = idCamera;
	}

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}


}