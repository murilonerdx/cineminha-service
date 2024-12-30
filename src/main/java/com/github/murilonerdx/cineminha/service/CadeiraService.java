package com.github.murilonerdx.cineminha.service;

import com.github.murilonerdx.cineminha.model.Assento;
import com.github.murilonerdx.cineminha.model.Reserva;
import com.github.murilonerdx.cineminha.model.ReservaResponse;
import com.github.murilonerdx.cineminha.model.Sala;
import com.github.murilonerdx.cineminha.repository.CadeiraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CadeiraService {
	private final CadeiraRepository cadeiraRepository;
	private final ReservaService reservaService;
	private final SalaService salaService;

	public CadeiraService(CadeiraRepository cadeiraRepository, ReservaService reservaService, SalaService salaService) {
		this.cadeiraRepository = cadeiraRepository;
		this.reservaService = reservaService;
		this.salaService = salaService;
	}

	public Stream<Assento> buscarAssentosFiltrados(List<String> numeros, List<Assento> assentos) {
		return assentos.stream().filter(assento -> numeros.contains(assento.getColuna() + assento.getNumero()));
	}

	@Transactional
	public ReservaResponse reservarCadeira(List<String> numeros, Long idSala, String cpfReserva) throws Exception {
		Sala sala = salaService.findById(idSala);
		if (sala.getFilmes() != null) {
			try {
				List<Assento> assentos = cadeiraRepository.findBySala(sala);

				boolean cadeiraJaReservada = buscarAssentosFiltrados(numeros, assentos).anyMatch(Assento::getReservada);
				List<Assento> assentosReservados = buscarAssentosFiltrados(numeros, assentos).filter(Assento::getReservada).toList();

				if (cadeiraJaReservada) {
					throw new IllegalStateException("Cadeira já reservada: " + assentosReservados);
				}

				List<Assento> peek = buscarAssentosFiltrados(numeros, assentos).peek(t -> t.setReservada(true)).toList();
				List<Assento> assentosReservada = cadeiraRepository.saveAll(peek);

				// Reserva a cadeira
				Reserva reserva = reservaService.salvar(new Reserva(null, sala.getId(), assentosReservada.stream().map(Assento::getId).toList(), cpfReserva));

				return new ReservaResponse(reserva, assentosReservada);
			} catch (RuntimeException e) {
				List<Assento> assentos = cadeiraRepository.findBySala(sala);
				cadeiraRepository.saveAll(assentos.stream().filter(assento -> numeros.contains(assento.getColuna() + assento.getNumero())).peek(t -> t.setReservada(false)).toList());
				throw new RuntimeException("Não foi possivel concluir a operação");
			}

		} else {
			throw new Exception("Essa sala ainda não tem filmes vinculados");
		}
	}

	@Transactional
	public List<Assento> findAll() {
		return cadeiraRepository.findAll();
	}
}
