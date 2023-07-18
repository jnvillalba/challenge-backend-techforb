package com.challengeBackend.techforb.DTO;

import com.challengeBackend.techforb.models.TipoTransaccion;
import java.time.LocalDateTime;
public class TransaccionDTO {

    private double monto;
    private TipoTransaccion tipo;
    private String descripcion;
    private LocalDateTime fecha;
    private UserDTO usuario;

    public TransaccionDTO() {
    }

    public TransaccionDTO( double monto, TipoTransaccion tipo, String descripcion, LocalDateTime fecha, UserDTO usuario) {

        this.monto = monto;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }
}
