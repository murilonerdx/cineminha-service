package com.github.murilonerdx.cineminha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequest {
	String cpf;
	List<String> numeroCadeira;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<String> getNumeroCadeira() {
		return numeroCadeira;
	}

	public void setNumeroCadeira(List<String> numeroCadeira) {
		this.numeroCadeira = numeroCadeira;
	}
}
