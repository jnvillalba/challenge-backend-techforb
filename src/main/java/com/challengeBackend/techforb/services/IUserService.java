package com.challengeBackend.techforb.services;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(int id);

    Optional<User> findByNroDocumento(int nroDocumento);

    User saveUser(User user);

    List<User> findAll();

    void deleteUser(int id);

    boolean existsById(int id);
    boolean existsByNroDocumento(int nroDocumento);
    void realizarTransferencia(int idUsuarioRemitente, int idUsuarioDestinatario, double cantidad)  throws SaldoInsuficienteException, UsuarioNoExisteException;

    void ExtraerDinero(int userId, double amount);

    void depositarDinero(int userId, double amount);

    void addTarjeta(int userId, Tarjeta tarjeta);
}
