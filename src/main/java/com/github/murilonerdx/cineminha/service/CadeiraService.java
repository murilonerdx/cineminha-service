package com.github.murilonerdx.cineminha.service;

import com.github.murilonerdx.cineminha.SalaNotFound;
import com.github.murilonerdx.cineminha.model.Cadeira;
import com.github.murilonerdx.cineminha.model.Reserva;
import com.github.murilonerdx.cineminha.model.ReservaResponse;
import com.github.murilonerdx.cineminha.model.Sala;
import com.github.murilonerdx.cineminha.repository.CadeiraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Transactional
	public ReservaResponse reservarCadeira(String numero, Long idSala, String cpfReserva) throws Exception {
		Sala sala = salaService.findById(idSala);
		if(sala.getFilme() != null ){
			Cadeira cadeira = cadeiraRepository.findBySalaAndNumero(sala, numero);
			boolean cadeiraJaReservada = cadeira.isReservada();

			if(cadeiraJaReservada){
				throw new IllegalStateException("Cadeira já reservada: " + numero);
			}

			cadeira.setReservada(true);

			// Reserva a cadeira
			Cadeira cadeiraReservada = cadeiraRepository.save(cadeira);
			Reserva reserva = reservaService.salvar(new Reserva(null, sala.getId(), cadeira.getId(), cpfReserva));

			return new ReservaResponse(reserva, cadeiraReservada);
		}else{
			throw new Exception("Essa sala ainda não tem filmes vinculados");
		}
	}

	@Transactional
	public List<Cadeira> findAll() {
		return cadeiraRepository.findAll();
	}
}
