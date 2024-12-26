package com.github.murilonerdx.cineminha.model;

import lombok.Data;

@Data

public class FilmeRequest {
	private String nome;
	private String descricao;
	private Long idadeMinima;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(Long idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public Filme toModel(Long id) throws Exception {
		return new Filme(id, this.nome, this.descricao, this.idadeMinima, null);
	}
}
