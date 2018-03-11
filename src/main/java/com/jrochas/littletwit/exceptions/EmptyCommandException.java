package com.jrochas.littletwit.exceptions;

public class EmptyCommandException extends Exception {

    public EmptyCommandException() {
        super("Command is empty");
    }

}
