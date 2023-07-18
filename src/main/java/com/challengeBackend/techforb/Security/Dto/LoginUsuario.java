package com.challengeBackend.techforb.Security.Dto;

import com.challengeBackend.techforb.Security.Enums.TipoDeDocumento;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank
    private TipoDeDocumento tipoDocumento;
    @NotBlank
    private int nroDocumento;
    
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
