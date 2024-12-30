package com.github.murilonerdx.cineminha.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@ElementCollection
	private List<String> generos = new ArrayList<>();

	private Long duracao; // Em minutos
	private String classificacao;
	private String posterUrl;
	private Long idadeMinima;
	private Boolean emCartaz = true;

	@JsonManagedReference
	@ManyToMany(mappedBy = "filmes", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Sala> salas = new ArrayList<>();


	public Filme(Long id, String nome, String sinopse, List<String> generos, Long duracao, String classificacao, String posterUrl, Long idadeMinima, Boolean emCartaz, List<Sala> salas) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.generos = generos;
		this.duracao = duracao;
		this.classificacao = classificacao;
		this.posterUrl = posterUrl;
		this.idadeMinima = idadeMinima;
		this.emCartaz = emCartaz;
		this.salas = salas;
	}

	public Filme() {
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

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
}
