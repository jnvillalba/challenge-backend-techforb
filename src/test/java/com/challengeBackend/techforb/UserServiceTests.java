package com.challengeBackend.techforb;
import static org.junit.jupiter.api.Assertions.*;

import com.challengeBackend.techforb.exceptions.SaldoInsuficienteException;
import com.challengeBackend.techforb.exceptions.UsuarioNoExisteException;
import com.challengeBackend.techforb.models.Tarjeta;
import com.challengeBackend.techforb.models.TipoTransaccion;
import com.challengeBackend.techforb.models.Transaccion;
import com.challengeBackend.techforb.models.User;
import com.challengeBackend.techforb.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootTest
@Transactional
public class UserServiceTests {
    //Se puede usar estos test para no tener que crear modelos para ver en el front
    @Autowired
    private UserServiceImpl userService;
    LocalDate fechaV = LocalDate.of(2023, 7, 20);
    Tarjeta tarjeta = new Tarjeta("1234567890",fechaV,"123","John Doe",null);
    User john = new User("John", "Doe", 123456789, new HashSet<>(), new HashSet<>(), 500.0);

    User jane = new User("Jane", "Doe", 123456788, new HashSet<>(), new HashSet<>(), 1000.0);

    @Test
    @Rollback(false)
    public void guardarUsuario() {
        var usuario = userService.saveUser(john);

        Assert.assertNotNull(usuario.getId());
        Assert.assertEquals(usuario.getNroDocumento(), john.getNroDocumento());
    }

    @Test
    @Rollback(false)
    public void testRealizarTransferenciaSaldoSuficienteTransaccionExitosa() throws Exception {

        var johnId = userService.findByNroDocumento(john.getNroDocumento()).get().getId();
        var janeId = userService.findByNroDocumento(jane.getNroDocumento()).get().getId();
        userService.realizarTransferencia(janeId, johnId, 105, "varios");

        User remitente = userService.findById(janeId).orElseThrow();
        User destinatario = userService.findById(johnId).orElseThrow();

        assertEquals(1000.0 - 100, remitente.getBalance(), 0.001);
        assertEquals(500.0 + 100, destinatario.getBalance(), 0.001);
    }

    @Test
    @Rollback(false)
    public void testExtraerDinero_SaldoSuficiente() throws UsuarioNoExisteException, SaldoInsuficienteException {

        double cantidadAExtraer = 105.0;
        var johnId = userService.findByNroDocumento(john.getNroDocumento()).get().getId();
        userService.extraerDinero(johnId, cantidadAExtraer);


        User usuarioActualizado = userService.findById(johnId).orElseThrow();
        assertEquals(500, usuarioActualizado.getBalance(), 0.001);
    }

    @Test
    @Rollback(false)
    public void testIngresarDinero_SaldoSuficiente() throws UsuarioNoExisteException, SaldoInsuficienteException {

        double cantidadAingresar = 110.0;
        var johnId = userService.findByNroDocumento(john.getNroDocumento()).get().getId();
        userService.depositarDinero(johnId, cantidadAingresar);

        User usuarioActualizado = userService.findById(johnId).orElseThrow();
        assertEquals(610, usuarioActualizado.getBalance(), 0.001);
    }

    @Test
    @Rollback(false)
    public void testAddTarjetaAJohn() throws UsuarioNoExisteException {
        var johnId = userService.findByNroDocumento(john.getNroDocumento()).get().getId();
        userService.addTarjeta(johnId, tarjeta);

        User usuarioActualizado = userService.findById(johnId).orElseThrow();
        int numeroTarjetasDespues = usuarioActualizado.getTarjetas().size();
        assertEquals(john.getTarjetas().size() + 1, numeroTarjetasDespues);
    }

}