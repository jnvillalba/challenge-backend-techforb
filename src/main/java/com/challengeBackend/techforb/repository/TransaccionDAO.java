package com.challengeBackend.techforb.repository;

import com.challengeBackend.techforb.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionDAO extends JpaRepository<Transaccion,Integer> {
}
