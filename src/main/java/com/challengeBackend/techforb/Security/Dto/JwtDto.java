
package com.challengeBackend.techforb.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
    
    private String token;
    private String bearer = "Bearer";
    private String nroDocumento;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nroDocumento, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nroDocumento = nroDocumento;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    
    
}
