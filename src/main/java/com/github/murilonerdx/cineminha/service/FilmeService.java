package com.github.murilonerdx.cineminha.service;

import com.github.murilonerdx.cineminha.model.Filme;
import com.github.murilonerdx.cineminha.model.FilmeRequest;
import com.github.murilonerdx.cineminha.model.Sala;
import com.github.murilonerdx.cineminha.repository.FilmeRepository;
import com.github.murilonerdx.cineminha.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

	private final FilmeRepository filmeRepository;
	private final SalaRepository salaService;
	private final SalaRepository salaRepository;

	public FilmeService(FilmeRepository filmeRepository, SalaRepository salaService, SalaRepository salaRepository) {
		this.filmeRepository = filmeRepository;
		this.salaService = salaService;
		this.salaRepository = salaRepository;
	}

	public Filme criarFilme(FilmeRequest filme, Long idSala) throws Exception {
		Sala sala = salaService.findById(idSala).orElse(null);
		if (sala == null) {
			throw new Exception("Sala não existe");
		}
		if (!sala.getReservada()) {
			sala.setReservada(true);
			Filme model = filme.toModel(null);
			sala.setFilmes(List.of(model));
			model.setSalas(List.of(sala));

			return filmeRepository.save(model);
		} else {
			throw new Exception("Sala já está reservada");
		}

	}

	public void deletarFilme(Long id) {
		filmeRepository.deleteById(id);
	}

	public Optional<Filme> findById(Long id) {
		return filmeRepository.findById(id);
	}

	public List<Filme> findAll() {
		return filmeRepository.findAll();
	}

	public Filme criarFilmeSemSala(FilmeRequest filmeRequest) throws Exception {
		Filme model = filmeRequest.toModel(null);
		return filmeRepository.save(model);
	}

	public Filme adicionarFilmeNaSala(Long salaId, Long filmeId) throws Exception {
		Sala sala = salaService.findById(salaId).orElse(null);
		Optional<Filme> byId = findById(filmeId);

		if(sala != null && sala.getReservada()){
			throw new Exception("Sala já foi reservada!!");
		}

		if (sala != null && byId.isPresent()) {
			sala.setReservada(true);
			sala.getFilmes().add(byId.get());
			byId.get().getSalas().add(sala);
			return filmeRepository.save(byId.get());
		}else{
			throw new Exception("Tente mais tarde!!");
		}
	}
}
