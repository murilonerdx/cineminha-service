package com.github.murilonerdx.cineminha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequest {
	String cpf;
	String numeroCadeira;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroCadeira() {
		return numeroCadeira;
	}

	public void setNumeroCadeira(String numeroCadeira) {
		this.numeroCadeira = numeroCadeira;
	}
}
