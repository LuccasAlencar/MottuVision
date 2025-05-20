package com.sprint.mottu.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CargoDTO {

    private Long id;

    @NotBlank(message = "Nome do cargo é obrigatório")
    @Size(max = 50, message = "Nome do cargo deve ter no máximo 50 caracteres")
    private String nome;

    @NotNull(message = "Nível de permissão deve ser informado")
    @Min(value = 1, message = "Nível de permissão mínimo é 1")
    @Max(value = 10, message = "Nível de permissão máximo é 10")
    private Integer nivelPermissao;

    private String permissoes;

    public CargoDTO() {}
    
    public CargoDTO(Long id, String nome, Integer nivelPermissao, String permissoes) {
        this.id = id;
        this.nome = nome;
        this.nivelPermissao = nivelPermissao;
        this.permissoes = permissoes;
    }

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