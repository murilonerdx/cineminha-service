package com.github.murilonerdx.cineminha.model;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum SalasCapacidade {
	SALA_A1(20), SALA_A2(10), SALA_A3(30), SALA_A4(15), SALA_A5(50);

	private final Integer capacidade;

	SalasCapacidade(Integer i) {
		this.capacidade = i;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public static SalasCapacidade getCapacidadeEnum(String nomeSala) throws Exception {
		try {
			return SalasCapacidade.valueOf(nomeSala);
		} catch (IllegalArgumentException e) {
			throw new Exception(String.format("Nome da sala %s não encontrado", nomeSala));
		}
	}

	public static List<SalaResponse> getResponse(){
		return Arrays.stream(values()).map(t -> new SalaResponse(t.name(), t.capacidade)).toList();
	}
}
