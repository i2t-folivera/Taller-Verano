package com.taller.nivelacion.competencia.exceptions;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super("Usuario inválido o no autorizado");
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(Throwable cause) {
        super("Usuario inválido o no autorizado", cause);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
