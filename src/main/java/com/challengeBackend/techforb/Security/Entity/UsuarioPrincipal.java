package com.challengeBackend.techforb.Security.Entity;

import com.challengeBackend.techforb.Security.Enums.TipoDeDocumento;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UsuarioPrincipal implements UserDetails {
    private final TipoDeDocumento tipoDocumento;
    private final int nroDocumento;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(TipoDeDocumento tipoDocumento, int nroDocumento, String password, Collection<? extends GrantedAuthority> authorities) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleNombre().name()))
                .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getTipoDocumento(), usuario.getNroDocumento(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return Integer.toString(nroDocumento);
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public TipoDeDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
