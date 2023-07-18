package com.challengeBackend.techforb.Security.Enums;

public enum TipoDeDocumento {
    DNI("Documento Nacional de Identidad"),
    CEDULA("Cédula de Identidad"),
    PASAPORTE("Pasaporte"),
    OTRO("Otro");

    private final String descripcion;

    TipoDeDocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
