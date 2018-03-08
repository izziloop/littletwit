package com.jrochas.littletwit;

public class InvalidInputException extends Exception {

    // pattern starts with a trimmed string plus a space plus a command with arguements

    // case 1 start your command by a user name

    // case 2 use a valid command

    // you cannot follow yourself

    public InvalidInputException(String message) {
        super(message);
    }
}
