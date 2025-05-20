package com.sprint.mottu.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;


@Entity
@Table(name = "reconhecimentos",
       indexes = @Index(name = "idx_reconhecimento_dataHora", columnList = "data_hora"))
public class Reconhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "Precisão é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "Precisão mínima é > 0")
    @DecimalMax(value = "1.0", message = "Precisão máxima é 1")
    private Double precisao;

    @NotBlank(message = "Imagem capturada é obrigatória")
    @Size(max = 255, message = "URL/Path deve ter no máximo 255 caracteres")
    @Column(name = "imagem_capturada", length = 255, nullable = false)
    private String imagemCapturada;

    @NotNull(message = "Confiança mínima é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "1.0")
    private Double confiancaMinima;

    @NotNull(message = "Câmera é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_camera", nullable = false)
    private Camera camera;

    @NotNull(message = "Moto é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    public Reconhecimento() {}

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

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}


}