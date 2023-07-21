package com.challengeBackend.techforb.services;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.Transaccion;
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
    void realizarTransferencia(int idUsuarioRemitente, int idUsuarioDestinatario, double cantidad, String motivo)  throws SaldoInsuficienteException, UsuarioNoExisteException;

    void extraerDinero(int userId, double amount) throws UsuarioNoExisteException, SaldoInsuficienteException;

    void depositarDinero(int userId, double amount) throws UsuarioNoExisteException;

    void addTarjeta(int userId, Tarjeta tarjeta) throws UsuarioNoExisteException;

     List<Transaccion> getTransactions(int userId);
}
