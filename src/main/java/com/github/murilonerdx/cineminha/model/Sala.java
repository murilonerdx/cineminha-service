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
	private boolean reservada = Boolean.FALSE;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "filme_id")
	private Filme filme;

	private Integer cadeiraPorFileira;


	@JsonManagedReference
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private List<Assento> assentos = new ArrayList<>();

	public Sala() {
	}

	public Sala(Long id, String nome, Integer capacidade, boolean reservada, Filme filme, List<Assento> assentos) {
		this.id = id;
		this.nome = nome;
		this.capacidade = capacidade;
		this.reservada = reservada;
		this.filme = filme;
		this.assentos = assentos;
	}

	public Integer getCadeiraPorFileira() {
		return cadeiraPorFileira;
	}

	public void setCadeiraPorFileira(Integer cadeiraPorFileira) {
		this.cadeiraPorFileira = cadeiraPorFileira;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isReservada() {
		return reservada;
	}

	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public Filme getFilme() {
		return filme;
	}

	public List<Assento> getAssentos() {
		return assentos;
	}

	public Boolean cadeiraReservada(String numero) {
		return getAssentos().stream().anyMatch(cadeira -> cadeira.getNumero().equals(numero) && cadeira.isReservada());
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


}
