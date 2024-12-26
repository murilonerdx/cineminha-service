package com.github.murilonerdx.cineminha.repository;

import com.github.murilonerdx.cineminha.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	List<Filme> findByEmCartazTrue();
}
