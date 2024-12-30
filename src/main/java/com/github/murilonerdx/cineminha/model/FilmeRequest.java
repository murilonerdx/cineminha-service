package com.github.murilonerdx.cineminha.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class FilmeRequest {
	private String nome;
	private String sinopse;
	private List<String> generos = new ArrayList<>();
	private Long duracao; // Em minutos
	private String classificacao;
	private String posterUrl;
	private Long idadeMinima;
	private Boolean emCartaz = true;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public Boolean getEmCartaz() {
		return emCartaz;
	}

	public void setEmCartaz(Boolean emCartaz) {
		this.emCartaz = emCartaz;
	}

	public Long getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(Long idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public Filme toModel(Long id) {
		return new Filme(
				id,
				this.nome,
				this.sinopse,
				this.generos,
				this.duracao,
				this.classificacao,
				this.posterUrl,
				this.idadeMinima,
				this.emCartaz,
				new ArrayList<>()
		);
	}
}
