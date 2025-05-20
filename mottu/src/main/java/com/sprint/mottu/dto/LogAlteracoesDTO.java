package com.sprint.mottu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LogAlteracoesDTO {

    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "ID da moto é obrigatório")
    private Long idMoto;

    @NotNull(message = "Campo alterado é obrigatório")
    @Size(max = 50, message = "Campo alterado deve ter no máximo 50 caracteres")
    private String campoAlterado;

    private String valorAntigo;
    private String valorNovo;

    public LogAlteracoesDTO() {}

    public LogAlteracoesDTO(Long id, LocalDateTime dataHora, Long idUsuario, Long idMoto,
                             String campoAlterado, String valorAntigo, String valorNovo) {
        this.id = id;
        this.dataHora = dataHora;
        this.idUsuario = idUsuario;
        this.idMoto = idMoto;
        this.campoAlterado = campoAlterado;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getCampoAlterado() {
		return campoAlterado;
	}

	public void setCampoAlterado(String campoAlterado) {
		this.campoAlterado = campoAlterado;
	}

	public String getValorAntigo() {
		return valorAntigo;
	}

	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	public String getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}

    
}