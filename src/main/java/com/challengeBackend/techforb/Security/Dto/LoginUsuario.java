package com.challengeBackend.techforb.Security.Dto;

import com.challengeBackend.techforb.Security.Enums.TipoDeDocumento;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LoginUsuario {
    @NotNull(message = "El campo tipoDocumento no puede estar vacío")
    private TipoDeDocumento tipoDocumento;
    @NotNull(message = "El campo nroDocumento no puede estar vacío")
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
