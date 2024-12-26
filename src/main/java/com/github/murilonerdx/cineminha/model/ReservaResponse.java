package com.github.murilonerdx.cineminha.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaResponse {
	Reserva reserva;
	Assento assento;

	public ReservaResponse(Reserva reserva, Assento assento) {
		this.reserva = reserva;
		this.assento = assento;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}
}
