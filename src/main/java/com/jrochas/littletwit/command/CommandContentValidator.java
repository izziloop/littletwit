package com.jrochas.littletwit.command;

import com.jrochas.littletwit.Properties;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import com.jrochas.littletwit.user.User;

public class CommandContentValidator {

    public void checkMessageSize(String message) throws InvalidInputException {
        if (message.length() > Properties.MESSAGES_MAX_LENGTH) {
            throw new InvalidInputException("Message is too long. Message limit is " + Properties.MESSAGES_MAX_LENGTH + " characters");
        }
    }

    public void checkUserNotNull(User user, String username) throws InvalidInputException {
        if (user == null) {
            throw new InvalidInputException("User " + username + " does not exist");
        }
    }

}
