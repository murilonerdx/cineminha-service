package com.github.murilonerdx.cineminha.service;
import com.github.murilonerdx.cineminha.SalaNotFound;
import com.github.murilonerdx.cineminha.model.Cadeira;
import com.github.murilonerdx.cineminha.model.Filme;
import com.github.murilonerdx.cineminha.model.Sala;
import com.github.murilonerdx.cineminha.model.SalasCapacidade;
import com.github.murilonerdx.cineminha.repository.CadeiraRepository;
import com.github.murilonerdx.cineminha.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalaService {
	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private CadeiraRepository cadeiraRepository;

	@Autowired
	private FilmeService filmeService;

	public Sala findById(Long id) throws SalaNotFound {
		return salaRepository.findById(id).orElseThrow(() -> new SalaNotFound(String.format("Sala %s não encontrada", id)));
	}

	public void deleteSala(Long id){
		salaRepository.deleteById(id);
	}

	public Sala criarSalaComCadeiras(String nomeSala, Integer cadeirasPorFileira, Long id) throws Exception {

		Sala salaEncontrada = salaRepository.findByNome(nomeSala);
		Filme filme = null;
		if(id != null){
			filme = filmeService.findById(id).orElse(null);
		}

		if(salaEncontrada != null){
			throw new Exception(String.format("Sala com nome %s já existe verifique outras salas", nomeSala));
		}else{
			// Criar a sala
			Integer capacidade = SalasCapacidade.getCapacidadeEnum(nomeSala).getCapacidade();
			Sala sala = new Sala();
			sala.setNome(nomeSala);
			sala.setCapacidade(capacidade);

			if(filme != null){
				sala.setFilme(filme);
			}

			// Salvar a sala no banco
			Sala salaSalva = salaRepository.save(sala);

			// Gerar cadeiras
			int fileiras = (int) Math.ceil((double) capacidade / cadeirasPorFileira); // Número de fileiras
			char letraInicial = 'A'; // Início das letras

			for (int i = 0; i < fileiras; i++) {
				char fileira = (char) (letraInicial + i); // Letra da fileira
				for (int j = 1; j <= cadeirasPorFileira; j++) {
					if ((i * cadeirasPorFileira + j) > capacidade) {
						break; // Não criar cadeiras além da capacidade
					}

					// Criar cadeira
					Cadeira cadeira = new Cadeira();
					cadeira.setNumero(fileira + String.valueOf(j)); // Ex: A1, A2...
					cadeira.setSala(salaSalva);

					// Salvar cadeira
					cadeiraRepository.save(cadeira);
				}
			}

			return salaSalva;
		}
	}

	public List<Sala> findAll() {
		return salaRepository.findAll();
	}
}
