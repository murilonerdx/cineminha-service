package com.github.murilonerdx.cineminha.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReservaResponse {
	Reserva reserva;
	List<Assento> assentos;

	public ReservaResponse(Reserva reserva, List<Assento> assentos) {
		this.reserva = reserva;
		this.assentos = assentos;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}
}
