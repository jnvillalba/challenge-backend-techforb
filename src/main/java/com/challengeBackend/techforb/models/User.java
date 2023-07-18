package com.challengeBackend.techforb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50, message = "No Cumple con la longitud")
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50, message = "No Cumple con la longitud")
    private String apellido;
    @NotNull
    private int nroDocumento;

    @OneToMany(mappedBy = "usuario")
    private Set<Transaccion> transacciones = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Tarjeta> tarjetas = new HashSet<>();

    private double balance;


    public User() {
    }

    public User(String nombre, String apellido, int nroDocumento, Set<Transaccion> transacciones, Set<Tarjeta> tarjetas, double balance) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroDocumento = nroDocumento;
        this.transacciones = transacciones;
        this.tarjetas = tarjetas;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Set<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
