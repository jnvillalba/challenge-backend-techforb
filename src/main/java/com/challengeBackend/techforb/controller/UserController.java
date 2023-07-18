package com.challengeBackend.techforb.controller;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.services.IUserService;
import com.challengeBackend.techforb.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) throws UsuarioNoExisteException {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            throw new UsuarioNoExisteException(id);
        }

        return userOptional.get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    //Update user ?

    @PostMapping("/{id}/transfer")
    public void realizarTransferencia(
            @PathVariable("id") int idUsuarioRemitente,
            @RequestParam("destinatario") int idUsuarioDestinatario,
            @RequestParam("cantidad") double cantidad
    ) throws SaldoInsuficienteException, UsuarioNoExisteException {
        userService.realizarTransferencia(idUsuarioRemitente, idUsuarioDestinatario, cantidad);
    }

    @PostMapping("/{id}/withdraw")
    public void extraerDinero(
            @PathVariable("id") int userId,
            @RequestParam("cantidad") double cantidad
    ) throws UsuarioNoExisteException, SaldoInsuficienteException {
        userService.extraerDinero(userId, cantidad);
    }

    @PostMapping("/{id}/deposit")
    public void depositarDinero(
            @PathVariable("id") int userId,
            @RequestParam("amount") double amount
    ) throws UsuarioNoExisteException {
        userService.depositarDinero(userId, amount);
    }

    @PostMapping("/{id}/cards")
    public void addTarjeta(
            @PathVariable("id") int userId,
            @RequestBody Tarjeta tarjeta
    ) throws UsuarioNoExisteException {
        userService.addTarjeta(userId, tarjeta);
    }
}
