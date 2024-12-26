package com.github.murilonerdx.cineminha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaResponse {
	Reserva reserva;
	Cadeira cadeira;

	public ReservaResponse(Reserva reserva, Cadeira cadeira) {
		this.reserva = reserva;
		this.cadeira = cadeira;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Cadeira getCadeira() {
		return cadeira;
	}

	public void setCadeira(Cadeira cadeira) {
		this.cadeira = cadeira;
	}
}
