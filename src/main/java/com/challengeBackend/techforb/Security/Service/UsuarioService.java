package com.challengeBackend.techforb.Security.Service;

import com.challengeBackend.techforb.Security.Entity.Usuario;
import com.challengeBackend.techforb.Security.Repository.IUsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    IUsuarioRepository iUsuarioRepository;
    
    public Optional<Usuario> getByNroDocumento(int nroDocumento){
        return iUsuarioRepository.findByNroDocumento(nroDocumento);
    }
    
    public Boolean exitsByNroDocumento(int nroDocumento){
        return iUsuarioRepository.exitsByNroDocumento(nroDocumento);
    }

    public void save(Usuario usuario){
        iUsuarioRepository.save(usuario);
    }
    
}
