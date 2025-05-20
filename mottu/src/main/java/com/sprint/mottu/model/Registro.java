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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "Câmera é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_camera", nullable = false)
    private Camera camera;

    @NotNull(message = "Moto é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @Size(max = 10, message = "Tipo de ação deve ter no máximo 10 caracteres")
    @Column(name = "tipo", length = 10)
    private String tipoAcao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reconhecimento", nullable = false) // Ou nullable dependendo da sua lógica
    private Reconhecimento reconhecimento;
    
    @Column(name = "modo_registro", length = 20) // Ajuste o tamanho conforme necessário
    private String modoRegistro;

    public String getModoRegistro() {
		return modoRegistro;
	}

	public void setModoRegistro(String modoRegistro) {
		this.modoRegistro = modoRegistro;
	}

	public Reconhecimento getReconhecimento() {
		return reconhecimento;
	}

	public void setReconhecimento(Reconhecimento reconhecimento) {
		this.reconhecimento = reconhecimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Registro() {}

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

	public String getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

    
}