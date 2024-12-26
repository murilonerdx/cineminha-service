package com.github.murilonerdx.cineminha.repository;
import com.github.murilonerdx.cineminha.model.Cadeira;
import com.github.murilonerdx.cineminha.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import jakarta.persistence.LockModeType;

import java.util.Optional;

@Repository
public interface CadeiraRepository extends JpaRepository<Cadeira, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Cadeira findBySalaAndNumero(Sala sala, String numero);
}
