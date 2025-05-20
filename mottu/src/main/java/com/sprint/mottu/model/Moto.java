package com.sprint.mottu.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;

@Entity
@Table(name = "motos",
       indexes = @Index(name = "idx_moto_placa", columnList = "placa"))
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A placa deve ser única e não pode ficar em branco
    @NotBlank(message = "Placa é obrigatória")
    @Size(max = 7, message = "Placa deve ter no máximo 7 caracteres")
    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    // Marca e modelo não podem ficar em branco
    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 50, message = "Modelo deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String modelo;

    // Cor é opcional
    @Size(max = 20, message = "Cor deve ter no máximo 20 caracteres")
    @Column(length = 20)
    private String cor;

    // Indica se a moto está presente ou não
    @NotNull(message = "Presença deve ser informada")
    @Column(nullable = false)
    private Boolean presente;

    // Data de cadastro, preenchida na hora de criar o registro
    @Column(name = "data_cadastro", updatable = false)
    private Instant dataCadastro;

    // Relacionamento 1:N com Registro (criamos a entidade Registro depois)
    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registro> registros;

    // Relacionamento 1:N com Reconhecimento
    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reconhecimento> reconhecimentos;
    
    @Column(name = "imagem_referencia")
    private String imagemReferencia;

    
    public String getImagemReferencia() {
		return imagemReferencia;
	}


	public void setImagemReferencia(String imagemReferencia) {
		this.imagemReferencia = imagemReferencia;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public Boolean getPresente() {
		return presente;
	}


	public void setPresente(Boolean presente) {
		this.presente = presente;
	}


	public Instant getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public List<Registro> getRegistros() {
		return registros;
	}


	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}


	public List<Reconhecimento> getReconhecimentos() {
		return reconhecimentos;
	}


	public void setReconhecimentos(List<Reconhecimento> reconhecimentos) {
		this.reconhecimentos = reconhecimentos;
	}



	@PrePersist
    protected void onCreate() {
        this.dataCadastro = Instant.now();
    }
}