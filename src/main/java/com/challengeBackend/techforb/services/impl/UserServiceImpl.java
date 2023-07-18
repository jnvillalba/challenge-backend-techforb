package com.challengeBackend.techforb.services.impl;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.repository.UserDAO;
import com.challengeBackend.techforb.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDAO userDAO;
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
    @Override
    public Optional<User> findById(int id) {
        return userDAO.findById(id);
    }
    @Override
    public Optional<User> findByNroDocumento(int nroDocumento) {
        return userDAO.findByNroDocumento(nroDocumento);
    }
    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }
    @Override
    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }
    @Override
    public boolean existsById(int id) {
        return userDAO.existsById(id);
    }

    @Override
    public boolean existsByNroDocumento(int nroDocumento) {
        return userDAO.existsByNroDocumento(nroDocumento);
    }

    @Override
    public void realizarTransferencia(int idUsuarioRemitente, int idUsuarioDestinatario, double cantidad) throws SaldoInsuficienteException, UsuarioNoExisteException {
        Optional<User> remitenteOptional = findById(idUsuarioRemitente);
        Optional<User> destinatarioOptional = findById(idUsuarioDestinatario);

        if (remitenteOptional.isPresent() && destinatarioOptional.isPresent()) {
            User remitente = remitenteOptional.get();
            User destinatario = destinatarioOptional.get();

            double saldoRemitente = remitente.getBalance();

            if (saldoRemitente >= cantidad) {
                double nuevoSaldoRemitente = saldoRemitente - cantidad;
                double nuevoSaldoDestinatario = destinatario.getBalance() + cantidad;

                remitente.setBalance(nuevoSaldoRemitente);
                destinatario.setBalance(nuevoSaldoDestinatario);
                saveUser(remitente);
                saveUser(destinatario);

            } else {
                throw new SaldoInsuficienteException();
            }
        } else {
            int idUsuarioNoExistente = remitenteOptional.isEmpty() ? idUsuarioRemitente : idUsuarioDestinatario;
            throw new UsuarioNoExisteException(idUsuarioNoExistente);
        }
    }

    @Override
    public void ExtraerDinero(int userId, double amount) {

    }

    @Override
    public void depositarDinero(int userId, double amount) {

    }
    @Override
    public void addTarjeta(int userId, Tarjeta tarjeta) {

    }
}
