package com.challengeBackend.techforb.services.impl;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.Transaccion;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.repository.TarjetaDAO;
import com.challengeBackend.techforb.repository.TransaccionDAO;
import com.challengeBackend.techforb.repository.UserDAO;
import com.challengeBackend.techforb.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    TarjetaDAO tarjetaDAO;

    @Autowired
    TransaccionDAO transaccionDAO;
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
    public void realizarTransferencia(int idUsuarioRemitente, int idUsuarioDestinatario, double cantidad, String motivo)
            throws SaldoInsuficienteException, UsuarioNoExisteException {
        User remitente = getUserById(idUsuarioRemitente);
        User destinatario = getUserById(idUsuarioDestinatario);

        double saldoRemitente = remitente.getBalance();

        if (saldoRemitente >= cantidad) {
            double nuevoSaldoRemitente = saldoRemitente - cantidad;
            double nuevoSaldoDestinatario = destinatario.getBalance() + cantidad;

            realizarTransaccion(remitente, destinatario, cantidad, motivo);

            remitente.actualizarSaldo(nuevoSaldoRemitente);
            destinatario.actualizarSaldo(nuevoSaldoDestinatario);

            saveUser(remitente);
            saveUser(destinatario);
        } else {
            throw new SaldoInsuficienteException();
        }
    }

    private void realizarTransaccion(User remitente, User destinatario, double cantidad, String motivo) {
        Transaccion transaccion = new Transaccion();
        transaccion.setMotivo(motivo);
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setUsuarioRemitente(remitente);
        transaccion.setUsuarioDestinatario(destinatario);

        remitente.addTransaccionSaliente(transaccion);
        destinatario.addTransaccionEntrantes(transaccion);

        transaccionDAO.save(transaccion);
    }

    @Override
    public void extraerDinero(int userId, double cantidad) throws UsuarioNoExisteException, SaldoInsuficienteException {
        User usuario = getUserById(userId);
        usuario.extraerDinero(cantidad);
        saveUser(usuario);
    }

    @Override
    public void depositarDinero(int userId, double cantidad) throws UsuarioNoExisteException {
        User usuario = getUserById(userId);
        usuario.depositarDinero(cantidad);
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
