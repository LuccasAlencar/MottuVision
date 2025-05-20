package com.sprint.mottu.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;


@Entity
@Table(name = "log_alteracoes",
       indexes = @Index(name = "idx_log_dataHora", columnList = "data_hora"))
public class LogAlteracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "Campo alterado é obrigatório")
    @Size(max = 50, message = "Campo alterado deve ter no máximo 50 caracteres")
    @Column(name = "campo_alterado", length = 50, nullable = false)
    private String campoAlterado;

    @Lob
    @Column(name = "valor_antigo")
    private String valorAntigo;

    @Lob
    @Column(name = "valor_novo")
    private String valorNovo;

    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull(message = "Moto é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;
    
    @NotNull(message = "Tipo de ação é obrigatório")
    @Size(max = 20, message = "Tipo de ação deve ter no máximo 20 caracteres")
    @Column(name = "tipo_acao", length = 20, nullable = false)
    private String tipoAcao;

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public LogAlteracoes() {}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

    
}