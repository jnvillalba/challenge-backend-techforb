package com.challengeBackend.techforb.exceptions;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("No tienes saldo suficiente para realizar la transferencia.");
    }
}
