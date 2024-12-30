package com.github.murilonerdx.cineminha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer capacidade;
	private Integer cadeiraPorFileira;
	private Boolean reservada = Boolean.FALSE;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "sala_filme",
			joinColumns = @JoinColumn(name = "sala_id"),
			inverseJoinColumns = @JoinColumn(name = "filme_id")
	)
	private List<Filme> filmes = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Assento> assentos = new ArrayList<>();


	public Sala() {
	}

	public Sala(Long id, String nome, Integer capacidade, Integer cadeiraPorFileira, Boolean reservada, List<Filme> filmes, List<Assento> assentos) {
		this.id = id;
		this.nome = nome;
		this.capacidade = capacidade;
		this.cadeiraPorFileira = cadeiraPorFileira;
		this.reservada = reservada;
		this.filmes = filmes;
		this.assentos = assentos;
	}


	public Boolean cadeiraReservada(String numero) {
		return getAssentos().stream().filter(cadeira -> cadeira != null).anyMatch(cadeira -> cadeira.getNumero().equals(numero) && cadeira.getReservada());
	}

	public Optional<Assento> getCadeira(String numero) {
		return getAssentos()
				.stream()
				.filter(cadeira -> cadeira.getNumero().equals(numero))
				.findFirst();
	}

	public Assento reservarCadeira(String numero) {
		if (!cadeiraReservada(numero)) {
			return getCadeira(numero).get();
		} else {
			return null;
		}
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

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Integer getCadeiraPorFileira() {
		return cadeiraPorFileira;
	}

	public void setCadeiraPorFileira(Integer cadeiraPorFileira) {
		this.cadeiraPorFileira = cadeiraPorFileira;
	}

	public Boolean getReservada() {
		return reservada;
	}

	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}
}
