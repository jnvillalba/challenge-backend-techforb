package com.challengeBackend.techforb.exceptions;

public class UsuarioNoExisteException extends Exception {
    public UsuarioNoExisteException(int idUsuario) {
        super("El usuario de id:'" + idUsuario + "' no existe.");
    }
}
