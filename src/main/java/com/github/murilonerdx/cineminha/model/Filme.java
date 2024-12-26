package com.github.murilonerdx.cineminha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String sinopse;

	@ElementCollection()
	private List<String> generos;
	private Long duracao;
	private String classificacao;
	private String posterUrl;
	private Long idadeMinima;
	private Boolean emCartaz = true;

	@OneToMany(mappedBy="filme", cascade = CascadeType.MERGE)
	private List<Sala> salas = new ArrayList<>();

	public Filme() {
	}

	public Filme(Long id, String nome, String sinopse, Long idadeMinima, Boolean emCartaz, List<Sala> salas) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.idadeMinima = idadeMinima;
		this.emCartaz = emCartaz;
		this.salas = salas;
	}

	public Filme(Long id, String nome, String sinopse, Long idadeMinima, List<Sala> salas) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.idadeMinima = idadeMinima;
		this.salas = salas;

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

	public String getDescricao() {
		return sinopse;
	}

	public void setDescricao(String sinopse) {
		this.sinopse = sinopse;
	}

	public Long getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(Long idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public Boolean getEmCartaz() {
		return emCartaz;
	}

	public void setEmCartaz(Boolean emCartaz) {
		this.emCartaz = emCartaz;
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

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
}
