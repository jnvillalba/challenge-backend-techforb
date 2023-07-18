package com.challengeBackend.techforb.models;

public enum TipoTransaccion {
    INGRESO("Ingreso"),
    EGRESO("Egreso");

    private final String descripcion;

    TipoTransaccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

