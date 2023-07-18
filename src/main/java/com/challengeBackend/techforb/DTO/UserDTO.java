package com.challengeBackend.techforb.DTO;

import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.Transaccion;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private String nombre;
    private String apellido;
    private int nroDocumento;
    private Set<TransaccionDTO> transacciones = new HashSet<>();
    private Set<TarjetaDTO> tarjetas = new HashSet<>();
    private double balance;

    public UserDTO() {
    }

    public UserDTO( String nombre, String apellido, int nroDocumento, Set<TransaccionDTO> transacciones, Set<TarjetaDTO> tarjetas, double balance) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.nroDocumento = nroDocumento;
        this.transacciones = transacciones;
        this.tarjetas = tarjetas;
        this.balance = balance;
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

    public Set<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }

    public Set<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(Set<TarjetaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
