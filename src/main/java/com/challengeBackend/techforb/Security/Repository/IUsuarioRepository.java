package com.challengeBackend.techforb.Security.Repository;

import com.challengeBackend.techforb.Security.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNroDocumento(int nroDocumento);

    boolean existsByNroDocumento(int nroDocumento);

    
}
