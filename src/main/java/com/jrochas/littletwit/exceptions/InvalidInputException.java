package com.jrochas.littletwit.exceptions;

public class InvalidInputException extends Exception {

    private static final String WARNING_SIGN = "/!\\ ";

    public InvalidInputException(String message) {
        super(WARNING_SIGN + message);
    }

}
