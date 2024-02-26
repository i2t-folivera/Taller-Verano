package com.nivelacion.taller.exceptions;

public class EmptyListException extends Exception {

    private static final String LIST_IS_EMPTY = "La lista está vacía";

    public EmptyListException() {
        super(LIST_IS_EMPTY);
    }

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(Throwable cause) {
        super(cause);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

}
