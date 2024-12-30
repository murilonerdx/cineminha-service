package com.github.murilonerdx.cineminha.repository;
import com.github.murilonerdx.cineminha.model.Assento;
import com.github.murilonerdx.cineminha.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import jakarta.persistence.LockModeType;

import java.util.List;

@Repository
public interface CadeiraRepository extends JpaRepository<Assento, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<Assento> findBySala(Sala sala);
}
