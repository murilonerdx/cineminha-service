package com.github.murilonerdx.cineminha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idSala;
	@ElementCollection
	private List<Long> idCadeiras;
	private String cpf;
	private LocalDate date = LocalDate.now();

	public Reserva(Long id, Long idSala, List<Long> idCadeiras, String cpf) {
		this.id = id;
		this.idSala = idSala;
		this.idCadeiras = idCadeiras;
		this.cpf = cpf;
	}

	public Reserva() {
	}

	public Reserva(Long id,Long idSala, List<Long> idCadeiras, String cpf, LocalDate date) {
		this.id = id;
		this.idSala = idSala;
		this.idCadeiras = idCadeiras;
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

	public List<Long> getIdCadeiras() {
		return idCadeiras;
	}

	public void setIdCadeiras(List<Long> idCadeiras) {
		this.idCadeiras = idCadeiras;
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
