package com.github.murilonerdx.cineminha.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cadeira {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	@JsonBackReference // Evita o ciclo
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@Column(nullable = false)
	private boolean reservada = false;

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(String numero) {
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

	public String getNumero() {
		return numero;
	}

	public Sala getSala() {
		return sala;
	}

	public boolean isReservada() {
		return reservada;
	}
}
