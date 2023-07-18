package com.challengeBackend.techforb.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double monto;
    private String motivo;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_remitente_id")
    private User usuarioRemitente;

    @ManyToOne
    @JoinColumn(name = "usuario_destinatario_id")
    private User usuarioDestinatario;

    public Transaccion() {}
    public Transaccion(double monto, String motivo, LocalDateTime fecha,
                       User usuarioRemitente, User usuarioDestinatario) {
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = fecha;
        this.usuarioRemitente = usuarioRemitente;
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public User getUsuarioRemitente() {
        return usuarioRemitente;
    }

    public void setUsuarioRemitente(User usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

    public User getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(User usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }


}

