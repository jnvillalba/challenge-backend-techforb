package com.challengeBackend.techforb.repository;

import com.challengeBackend.techforb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<User> findByNroDocumento(int nroDocumento);

    boolean existsByNroDocumento(int nroDocumento);
}