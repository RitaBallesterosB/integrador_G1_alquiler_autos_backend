package com.backend.apirest.autos.alquilerautos.exceptions;

public class ReservaNotFoundException extends RuntimeException {

    public ReservaNotFoundException() {
        super();
    }

    public ReservaNotFoundException(String message) {
        super(message);
    }

    public ReservaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservaNotFoundException(Throwable cause) {
        super(cause);
    }
}

