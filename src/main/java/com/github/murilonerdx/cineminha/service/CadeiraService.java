package com.github.murilonerdx.cineminha.service;

import com.github.murilonerdx.cineminha.model.Assento;
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
			Assento assento = cadeiraRepository.findBySalaAndNumeroColuna(sala, numero);
			boolean cadeiraJaReservada = assento.isReservada();

			if(cadeiraJaReservada){
				throw new IllegalStateException("Cadeira já reservada: " + numero);
			}

			assento.setReservada(true);

			// Reserva a cadeira
			Assento assentoReservada = cadeiraRepository.save(assento);
			Reserva reserva = reservaService.salvar(new Reserva(null, sala.getId(), assento.getId(), cpfReserva));

			return new ReservaResponse(reserva, assentoReservada);
		}else{
			throw new Exception("Essa sala ainda não tem filmes vinculados");
		}
	}

	@Transactional
	public List<Assento> findAll() {
		return cadeiraRepository.findAll();
	}
}
