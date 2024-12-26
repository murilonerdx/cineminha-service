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

	private Integer numero;
	private String coluna;
	private String numeroColuna;

	@JsonBackReference // Evita o ciclo
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@Column(nullable = false)
	private boolean reservada = false;


	public String getNumeroColuna() {
		return numeroColuna;
	}

	public void setNumeroColuna(String numeroColuna) {
		this.numeroColuna = numeroColuna;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}

	public Long getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public Sala getSala() {
		return sala;
	}

	public boolean isReservada() {
		return reservada;
	}
}
