package com.challengeBackend.techforb.Security.Service;

import com.challengeBackend.techforb.Security.Entity.Usuario;
import com.challengeBackend.techforb.Security.Entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        int numero = Integer.parseInt(nombreUsuario);
        Usuario usuario = usuarioService.getByNroDocumento(numero).get();
        return UsuarioPrincipal.build(usuario);
    }
}
