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

	@OneToOne(mappedBy = "filme", cascade = CascadeType.MERGE)
	private Sala sala;

	public Filme() {
	}

	public Filme(Long id, String nome, String sinopse, Long idadeMinima, Boolean emCartaz, Sala sala) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.idadeMinima = idadeMinima;
		this.emCartaz = emCartaz;
		this.sala = sala;
	}

	public Filme(Long id, String nome, String sinopse, Long idadeMinima, Sala sala) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.idadeMinima = idadeMinima;
		this.sala = sala;

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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
