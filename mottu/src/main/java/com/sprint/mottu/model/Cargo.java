package com.sprint.mottu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;


@Entity
@Table(name = "cargos",
       indexes = @Index(name = "idx_cargo_nome", columnList = "nome"))
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do cargo é obrigatório")
    @Size(max = 50, message = "Nome do cargo deve ter no máximo 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @NotNull(message = "Nível de permissão deve ser informado")
    @Min(value = 1, message = "Nível de permissão mínimo é 1")
    @Max(value = 10, message = "Nível de permissão máximo é 10")
    @Column(nullable = false)
    private Integer nivelPermissao;

    @Lob
    private String permissoes;  // JSON ou CSV com permissões detalhadas

    public Cargo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNivelPermissao() {
		return nivelPermissao;
	}

	public void setNivelPermissao(Integer nivelPermissao) {
		this.nivelPermissao = nivelPermissao;
	}

	public String getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}


}