package com.challengeBackend.techforb.DTO;

import java.time.LocalDate;

public class TarjetaDTO {
    private String numero;
    private String titular;
    private LocalDate fechaVencimiento;
    private String codigoSeguridad;
    private UserDTO usuario;

    public TarjetaDTO(String numero, String titular, LocalDate fechaVencimiento, String codigoSeguridad, UserDTO usuario) {
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }
}
