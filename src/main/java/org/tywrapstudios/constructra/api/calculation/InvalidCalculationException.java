package org.tywrapstudios.constructra.api.calculation;

public class InvalidCalculationException extends Exception {
    public InvalidCalculationException(String message) {
        super(message);
    }

    public InvalidCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
