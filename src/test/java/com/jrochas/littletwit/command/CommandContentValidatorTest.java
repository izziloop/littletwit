package com.jrochas.littletwit.command;

import com.jrochas.littletwit.Properties;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import com.jrochas.littletwit.user.User;
import org.junit.Before;
import org.junit.Test;

public class CommandContentValidatorTest {

    private CommandContentValidator commandContentValidator;

    @Before
    public void setup() {
        this.commandContentValidator = new CommandContentValidator();
    }

    @Test(expected = InvalidInputException.class)
    public void testCheckMessageSizeThrowsException() throws InvalidInputException {
        char[] invalidMessage = new char[Properties.MESSAGES_MAX_LENGTH + 10];
        this.commandContentValidator.checkMessageSize(new String(invalidMessage));
    }

    @Test
    public void testCheckMessageSizeDoesNotThrowException() throws InvalidInputException {
        String validMessage = "This message is valid";
        this.commandContentValidator.checkMessageSize(validMessage);
    }

    @Test(expected = InvalidInputException.class)
    public void testCheckUserNotNullThrowsException() throws InvalidInputException {
        User invalidUser = null;
        String username = "username";
        this.commandContentValidator.checkUserNotNull(invalidUser, username);
    }

    @Test
    public void testCheckUserNotNullDoesNotThrowException() throws InvalidInputException {
        String username = "username";
        User validUser = new User(username);
        this.commandContentValidator.checkUserNotNull(validUser, username);
    }

    @Test(expected = InvalidInputException.class)
    public void testUserCannotFollowHimself() throws InvalidInputException {
        String username = "username";
        User validUser = new User(username);
        this.commandContentValidator.checkFollowedUserIsNotUser(validUser, validUser);
    }

}
