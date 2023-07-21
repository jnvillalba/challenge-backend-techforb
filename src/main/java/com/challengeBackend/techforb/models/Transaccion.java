package com.challengeBackend.techforb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private TipoTransaccion tipo;
    private String motivo;
    private LocalDateTime fecha;

    private String estado;
    @ManyToOne
    @JoinColumn(name = "usuario_remitente_id")
    @JsonIgnoreProperties("transacciones")
    private User usuarioRemitente;

    @ManyToOne
    @JoinColumn(name = "usuario_destinatario_id")
    @JsonIgnoreProperties("transacciones")
    private User usuarioDestinatario;

    public Transaccion() {}

    public Transaccion(TipoTransaccion tipo, String motivo, LocalDateTime fecha, String estado, User usuarioRemitente, User usuarioDestinatario) {
        this.tipo = tipo;
        this.motivo = motivo;
        this.fecha = fecha;
        this.estado = estado;
        this.usuarioRemitente = usuarioRemitente;
        this.usuarioDestinatario = usuarioDestinatario;
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


    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

