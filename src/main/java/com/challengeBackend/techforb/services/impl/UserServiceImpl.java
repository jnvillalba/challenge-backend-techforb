package com.challengeBackend.techforb.services.impl;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.repository.TarjetaDAO;
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

    @Autowired
    TarjetaDAO tarjetaDAO;
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
    public void realizarTransferencia(int idUsuarioRemitente, int idUsuarioDestinatario, double cantidad)
            throws SaldoInsuficienteException, UsuarioNoExisteException {
        User remitente = getUserById(idUsuarioRemitente);
        User destinatario = getUserById(idUsuarioDestinatario);

        double saldoRemitente = remitente.getBalance();

        if (saldoRemitente >= cantidad) {
            double nuevoSaldoRemitente = saldoRemitente - cantidad;
            double nuevoSaldoDestinatario = destinatario.getBalance() + cantidad;

            remitente.actualizarSaldo(nuevoSaldoRemitente);
            destinatario.actualizarSaldo(nuevoSaldoDestinatario);
            saveUser(remitente);
            saveUser(destinatario);
        } else {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public void extraerDinero(int userId, double cantidad) throws UsuarioNoExisteException, SaldoInsuficienteException {
        User usuario = getUserById(userId);
        usuario.extraerDinero(cantidad);
        saveUser(usuario);
    }

    @Override
    public void depositarDinero(int userId, double amount) throws UsuarioNoExisteException {
        User usuario = getUserById(userId);
        usuario.depositarDinero(amount);
        saveUser(usuario);
    }

    @Override
    public void addTarjeta(int userId, Tarjeta tarjeta) throws UsuarioNoExisteException {
        User usuario = getUserById(userId);
        usuario.addTarjeta(tarjeta);
        saveUser(usuario);
        tarjetaDAO.save(tarjeta);
    }

    private User getUserById(int userId) throws UsuarioNoExisteException {
        Optional<User> userOptional = findById(userId);

        if (userOptional.isEmpty()) {
            throw new UsuarioNoExisteException(userId);
        }

        return userOptional.get();
    }
}
