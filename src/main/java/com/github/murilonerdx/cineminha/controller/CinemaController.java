package com.github.murilonerdx.cineminha.controller;

import com.github.murilonerdx.cineminha.SalaNotFound;
import com.github.murilonerdx.cineminha.model.*;
import com.github.murilonerdx.cineminha.service.CadeiraService;
import com.github.murilonerdx.cineminha.service.FilmeService;
import com.github.murilonerdx.cineminha.service.ReservaService;
import com.github.murilonerdx.cineminha.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CinemaController {

	private final SalaService salaService;
	private final FilmeService filmeService;
	private final CadeiraService cadeiraService;
	private final ReservaService reservaService;

	public CinemaController(SalaService salaService, FilmeService filmeService, CadeiraService cadeiraService, ReservaService reservaService) {
		this.salaService = salaService;
		this.filmeService = filmeService;
		this.cadeiraService = cadeiraService;
		this.reservaService = reservaService;
	}

	// SALAS

	@GetMapping("/salas/{id}")
	public ResponseEntity<Sala> getSalaById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(salaService.findById(id));
		} catch (SalaNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/salas")
	public ResponseEntity<List<Sala>> getAllSalas() {
		return ResponseEntity.ok(salaService.findAll());
	}

	@PostMapping("/salas")
	public ResponseEntity<Sala> createSala(@RequestParam String nomeSala, @RequestParam Integer cadeirasPorFileira, @RequestParam(required = false) Long filmeId) throws Exception {
		Sala sala = salaService.criarSalaComCadeiras(nomeSala, cadeirasPorFileira, filmeId);
		return ResponseEntity.ok(sala);
	}

	@DeleteMapping("/salas/{id}")
	public ResponseEntity<Void> deleteSala(@PathVariable Long id) {
		try {
			salaService.deleteSala(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// FILMES

	@GetMapping("/filmes")
	public ResponseEntity<List<Filme>> getAllFilmes() {
		return ResponseEntity.ok(filmeService.findAll());
	}

	@PostMapping("/filmes")
	public ResponseEntity<Filme> createFilme(@RequestBody FilmeRequest filmeRequest, @RequestParam(required = false) Long idSala) throws Exception {
		Filme filme = idSala != null ? filmeService.criarFilme(filmeRequest, idSala) : filmeService.criarFilmeSemSala(filmeRequest);
		return ResponseEntity.ok(filme);
	}

	@DeleteMapping("/filmes/{id}")
	public ResponseEntity<Void> deleteFilme(@PathVariable Long id) {
		try {
			filmeService.deletarFilme(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// RESERVAS

	@GetMapping("/reservas/sala/{idSala}")
	public ResponseEntity<List<Reserva>> getReservasBySala(@PathVariable Long idSala, @RequestParam LocalDate data) {
		return ResponseEntity.ok(reservaService.findByIdSala(idSala, data));
	}

	@GetMapping("/reservas/cpf/{cpf}")
	public ResponseEntity<List<Reserva>> getReservasByCpf(@PathVariable String cpf, @RequestParam LocalDate data) {
		return ResponseEntity.ok(reservaService.findByCpf(cpf, data));
	}

	@GetMapping("/reservas/cadeira/{idCadeira}")
	public ResponseEntity<List<Reserva>> getReservasByCadeira(@PathVariable Long idCadeira, @RequestParam LocalDate data) {
		return ResponseEntity.ok(reservaService.findByIdCadeira(idCadeira, data));
	}

	@PostMapping("/reservas")
	public ResponseEntity<ReservaResponse> createReserva(@RequestParam Long idSala, @RequestBody ReservaRequest reservaRequest) {
		try {
			ReservaResponse reserva = cadeiraService.reservarCadeira(reservaRequest.getNumeroCadeira(), idSala, reservaRequest.getCpf());
			return ResponseEntity.ok(reserva);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/salas/{salaId}/adicionar-filme/{filmeId}")
	public ResponseEntity<Filme> adicionarFilmeNaSala(@PathVariable Long salaId, @PathVariable Long filmeId) throws Exception {
		Filme filme = filmeService.adicionarFilmeNaSala(salaId, filmeId);
		return ResponseEntity.ok(filme);
	}
}
