package com.github.murilonerdx.cineminha.service;

import com.github.murilonerdx.cineminha.model.Reserva;
import com.github.murilonerdx.cineminha.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {
	private final ReservaRepository reservaRepository;

	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	public List<Reserva> findByIdSala(Long idSala, LocalDate localDate) {
		return reservaRepository.findByIdSalaAndDate(idSala, localDate);
	}

	public List<Reserva> findByCpf(String cpf, LocalDate localDate) {
		return reservaRepository.findByCpfAndDate(cpf, localDate);
	}

	public List<Reserva> findByIdCadeira(Long idCadeira, LocalDate localDate) {
		return reservaRepository.findByIdCadeiraAndDate(idCadeira, localDate);
	}

	public Reserva salvar(Reserva reserva) {
		return reservaRepository.save(reserva);
	}
}
