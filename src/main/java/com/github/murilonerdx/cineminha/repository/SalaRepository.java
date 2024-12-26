package com.github.murilonerdx.cineminha.repository;

import com.github.murilonerdx.cineminha.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
	Sala findByNome(String nome);
}
