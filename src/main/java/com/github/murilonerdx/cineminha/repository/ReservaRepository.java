package com.github.murilonerdx.cineminha.repository;

import com.github.murilonerdx.cineminha.model.Filme;
import com.github.murilonerdx.cineminha.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	public List<Reserva> findByIdSalaAndDate(Long idSala, LocalDate date);
	public List<Reserva> findByIdCadeirasAndDate(List<Long> idCadeira, LocalDate date);
	public List<Reserva> findByCpfAndDate(String cpf, LocalDate date);
}
