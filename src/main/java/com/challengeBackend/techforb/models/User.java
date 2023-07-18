package com.challengeBackend.techforb.models;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "usuarioRemitente")
    private Set<Transaccion> transaccionesSalientes = new HashSet<>();

    @OneToMany(mappedBy = "usuarioDestinatario")
    private Set<Transaccion> transaccionesEntrantes = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Tarjeta> tarjetas = new HashSet<>();

    private double balance;


    public User() {
    }

    public User(String nombre, String apellido, int nroDocumento, Set<Transaccion> transaccionesRemitentes,
                Set<Transaccion> transaccionesDestinatarios, Set<Tarjeta> tarjetas, double balance) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroDocumento = nroDocumento;
        this.transaccionesSalientes = transaccionesRemitentes;
        this.transaccionesEntrantes = transaccionesDestinatarios;
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

    public Set<Transaccion> getTransaccionesSalientes() {
        return transaccionesSalientes;
    }

    public void setTransaccionesSalientes(Set<Transaccion> transaccionesSalientes) {
        this.transaccionesSalientes = transaccionesSalientes;
    }

    public Set<Transaccion> getTransaccionesEntrantes() {
        return transaccionesEntrantes;
    }

    public void setTransaccionesEntrantes(Set<Transaccion> transaccionesEntrantes) {
        this.transaccionesEntrantes = transaccionesEntrantes;
    }

    public void actualizarSaldo(double nuevoSaldo) {
        this.balance = nuevoSaldo;
    }

    public void extraerDinero(double cantidad) throws SaldoInsuficienteException {
        if (this.balance >= cantidad) {
            double nuevoSaldo = this.balance - cantidad;
            actualizarSaldo(nuevoSaldo);
        } else {
            throw new SaldoInsuficienteException();
        }
    }

    public void depositarDinero(double cantidad) {
        double nuevoSaldo = this.balance + cantidad;
        actualizarSaldo(nuevoSaldo);
    }

    public void addTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
        tarjeta.setUsuario(this);
    }

    public void addTransaccionSaliente(Transaccion transaccion) {
        this.transaccionesSalientes.add(transaccion);
    }

    public void addTransaccionEntrantes(Transaccion transaccion) {
        this.transaccionesEntrantes.add(transaccion);
    }
}
