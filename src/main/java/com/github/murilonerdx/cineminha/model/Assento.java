package com.github.murilonerdx.cineminha.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Assento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numero; // Número do assento
	private String coluna;  // Coluna na sala (ex.: A, B, C)
	private Boolean reservada = false;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;

	public Assento() {
	}

	public Assento(Long id, Integer numero, String coluna, Boolean reservada, Sala sala) {
		this.id = id;
		this.numero = numero;
		this.coluna = coluna;
		this.reservada = reservada;
		this.sala = sala;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public Boolean getReservada() {
		return reservada;
	}

	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
