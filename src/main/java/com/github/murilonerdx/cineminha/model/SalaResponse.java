package com.github.murilonerdx.cineminha.model;

public class SalaResponse {
	String nome;
	Integer capacidade;

	public SalaResponse(String nome, Integer capacidade) {
		this.nome = nome;
		this.capacidade = capacidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
}
