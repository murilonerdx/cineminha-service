package com.github.murilonerdx.cineminha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idSala;
	private Long idCadeira;
	private String cpf;
	private LocalDate date = LocalDate.now();

	public Reserva(Long id, Long idSala, Long idCadeira, String cpf) {
		this.id = id;
		this.idSala = idSala;
		this.idCadeira = idCadeira;
		this.cpf = cpf;
	}

	public Reserva() {
	}

	public Reserva(Long id, Long idSala, Long idCadeira, String cpf, LocalDate date) {
		this.id = id;
		this.idSala = idSala;
		this.idCadeira = idCadeira;
		this.cpf = cpf;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public Long getIdCadeira() {
		return idCadeira;
	}

	public void setIdCadeira(Long idCadeira) {
		this.idCadeira = idCadeira;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
