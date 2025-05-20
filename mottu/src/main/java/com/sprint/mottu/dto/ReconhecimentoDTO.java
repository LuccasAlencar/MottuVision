package com.sprint.mottu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReconhecimentoDTO {

    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "Precisão é obrigatória")
    private Double precisao;

    @NotBlank(message = "Imagem capturada é obrigatória")
    private String imagemCapturada;

    @NotNull(message = "Confiança mínima é obrigatória")
    private Double confiancaMinima;

    @NotNull(message = "ID da câmera é obrigatório")
    private Long idCamera;

    @NotNull(message = "ID da moto é obrigatório")
    private Long idMoto;

    public ReconhecimentoDTO() {}

    public ReconhecimentoDTO(Long id, LocalDateTime dataHora, Double precisao,
        String imagemCapturada, Double confiancaMinima, Long idCamera, Long idMoto) {
        this.id = id;
        this.dataHora = dataHora;
        this.precisao = precisao;
        this.imagemCapturada = imagemCapturada;
        this.confiancaMinima = confiancaMinima;
        this.idCamera = idCamera;
        this.idMoto = idMoto;
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

	public Double getPrecisao() {
		return precisao;
	}

	public void setPrecisao(Double precisao) {
		this.precisao = precisao;
	}

	public String getImagemCapturada() {
		return imagemCapturada;
	}

	public void setImagemCapturada(String imagemCapturada) {
		this.imagemCapturada = imagemCapturada;
	}

	public Double getConfiancaMinima() {
		return confiancaMinima;
	}

	public void setConfiancaMinima(Double confiancaMinima) {
		this.confiancaMinima = confiancaMinima;
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

    
}