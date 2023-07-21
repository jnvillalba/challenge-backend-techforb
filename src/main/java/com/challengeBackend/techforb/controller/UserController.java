package com.challengeBackend.techforb.controller;

import com.challengeBackend.techforb.Security.Controller.Mensaje;
import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.Transaccion;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws UsuarioNoExisteException {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            throw new UsuarioNoExisteException(id);
        }

        User user = userOptional.get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/transacciones")
    public ResponseEntity<List<Transaccion>> getTransactions(@PathVariable int id) {
        List<Transaccion> tranx = userService.getTransactions(id);
        return ResponseEntity.ok(tranx);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) throws UsuarioNoExisteException {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            throw new UsuarioNoExisteException(id);
        }

        User user = userOptional.get();
        user.setNombre(updatedUser.getNombre());
        user.setApellido(updatedUser.getApellido());
        user.setNroDocumento(updatedUser.getNroDocumento());
        user.setBalance(updatedUser.getBalance());

        userService.saveUser(user);
        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }

    @PostMapping("/{id}/transferir")
    public ResponseEntity<Void> realizarTransferencia(
            @PathVariable("id") int idUsuarioRemitente,
            @RequestParam("destinatario") int idUsuarioDestinatario,
            @RequestParam("cantidad") double cantidad,
            @RequestParam("motivo") String motivo
    ) throws SaldoInsuficienteException, UsuarioNoExisteException {
        userService.realizarTransferencia(idUsuarioRemitente, idUsuarioDestinatario, cantidad,motivo);
        return new ResponseEntity(new Mensaje("Transferencia Realizada"), HttpStatus.OK);
    }

    @PostMapping("/{id}/extraerDinero")
    public ResponseEntity<Void> extraerDinero(
            @PathVariable("id") int userId,
            @RequestParam("cantidad") double cantidad
    ) throws UsuarioNoExisteException, SaldoInsuficienteException {
        userService.extraerDinero(userId, cantidad);
        return new ResponseEntity(new Mensaje("Dinero Extraido"), HttpStatus.OK);
    }

    @PostMapping("/{id}/depositarDinero")
    public ResponseEntity<Void> depositarDinero(
            @PathVariable("id") int userId,
            @RequestParam("cantidad") double cantidad
    ) throws UsuarioNoExisteException {
        userService.depositarDinero(userId, cantidad);
        return new ResponseEntity(new Mensaje("Dinero Depositado"), HttpStatus.OK);
    }

    @PostMapping("/{id}/agregarTarjeta")
    public ResponseEntity<Void> addTarjeta(
            @PathVariable("id") int userId,
            @RequestBody Tarjeta tarjeta
    ) throws UsuarioNoExisteException {
        userService.addTarjeta(userId, tarjeta);
        return new ResponseEntity(new Mensaje("Tarjeta Agregada"), HttpStatus.OK);
    }


}
