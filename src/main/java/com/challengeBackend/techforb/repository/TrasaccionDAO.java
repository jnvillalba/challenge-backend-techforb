package com.challengeBackend.techforb.repository;

import com.challengeBackend.techforb.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasaccionDAO extends JpaRepository<Transaccion,Integer> {
}
