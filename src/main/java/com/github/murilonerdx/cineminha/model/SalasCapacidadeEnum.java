package com.github.murilonerdx.cineminha.model;


import java.util.Arrays;
import java.util.List;

public enum SalasCapacidadeEnum {
	SALA_A1(20, 6), SALA_A2(10,5), SALA_A3(30,5), SALA_A4(15,4), SALA_A5(50,6);

	private final Integer capacidade;
	private final Integer cadeiraPorFileira;

	SalasCapacidadeEnum(Integer capacidade, Integer cadeiraPorFileira) {
		this.capacidade = capacidade;
		this.cadeiraPorFileira = cadeiraPorFileira;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public Integer getCadeiraPorFileira() {
		return cadeiraPorFileira;
	}

	public static SalasCapacidadeEnum getCapacidadeEnum(String nomeSala) throws Exception {
		try {
			return SalasCapacidadeEnum.valueOf(nomeSala);
		} catch (IllegalArgumentException e) {
			throw new Exception(String.format("Nome da sala %s não encontrado", nomeSala));
		}
	}

	public static List<SalaResponse> getResponse(){
		return Arrays.stream(values()).map(t -> new SalaResponse(t.name(), t.capacidade)).toList();
	}
}
