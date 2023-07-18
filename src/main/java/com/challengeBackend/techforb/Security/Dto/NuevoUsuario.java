package com.challengeBackend.techforb.Security.Dto;

import com.challengeBackend.techforb.Security.Enums.TipoDeDocumento;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    
    private TipoDeDocumento tipoDocumento;
    
    private int nroDocumento;

    private String password;
    
    private Set<String> roles = new HashSet<>();


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    public TipoDeDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDeDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }
}
