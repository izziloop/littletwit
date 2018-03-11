package com.jrochas.littletwit.command;

import com.jrochas.littletwit.exceptions.EmptyCommandException;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class CommandParserTest {

    private static final String VALID_USERNAME = "John";

    private CommandParser commandParser;

    @Before
    public void setup() {
        this.commandParser = new CommandParser();
    }

    @Test
    public void testValidPostCommand() throws InvalidInputException, EmptyCommandException {
        ParsedCommand parsedCommand = this.commandParser.parse(VALID_USERNAME + " -> Hello world!");
        assertTrue(parsedCommand.getUsername().equals(VALID_USERNAME));
        assertTrue(parsedCommand.getOperator().equals(CommandOperator.POST_MESSAGE));
        assertTrue(parsedCommand.getParameter().equals("Hello world!"));
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidCommand() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse("Lorem ipseum " + CommandOperator.POST_MESSAGE.getReservedKeyword());
    }

    @Test
    public void testUsernameWithDigitOnlyIsAllowed() throws InvalidInputException, EmptyCommandException {
        ParsedCommand parsedCommand = this.commandParser.parse("  0123456789  ->     hello    ");
        assertTrue(parsedCommand.getUsername().equals("0123456789"));
        assertTrue(parsedCommand.getOperator().equals(CommandOperator.POST_MESSAGE));
        assertTrue(parsedCommand.getParameter().equals("hello"));
    }

    @Test(expected = InvalidInputException.class)
    public void testUsernameCannotBeEmpty() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse("    ->     hello");
    }

    @Test(expected = InvalidInputException.class)
    public void testUsernameCannotContainDashesNorUnderscores() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse("  toto-titi_tata  ->     hello");
    }

    @Test(expected = InvalidInputException.class)
    public void testUsernameCannotContainNonAplhanumericalCharacters() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse("  t*o%t$o  ->     hello");
    }

    @Test
    public void testValidViewTimelineCommand() throws InvalidInputException, EmptyCommandException {
        ParsedCommand parsedCommand = this.commandParser.parse(VALID_USERNAME);
        assertTrue(parsedCommand.getUsername().equals(VALID_USERNAME));
        assertTrue(parsedCommand.getOperator().equals(CommandOperator.VIEW_TIMELINE));
        assertTrue(parsedCommand.getParameter().equals(ParsedCommand.NO_PARAMETER));
    }

    @Test
    public void testUserFollowsValidUser() throws InvalidInputException, EmptyCommandException {
        ParsedCommand parsedCommand = this.commandParser.parse("John " + CommandOperator.FOLLOW_USER.getReservedKeyword() + " Doe");
        assertTrue(parsedCommand.getUsername().equals("John"));
        assertTrue(parsedCommand.getOperator().equals(CommandOperator.FOLLOW_USER));
        assertTrue(parsedCommand.getParameter().equals("Doe"));
    }

    @Test(expected = InvalidInputException.class)
    public void testUserFollowsIsCaseSensitive() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse("John FOLLOWS Doe");
    }

    @Test
    public void testValidDisplayWallCommand() throws InvalidInputException, EmptyCommandException {
        ParsedCommand parsedCommand = this.commandParser.parse(VALID_USERNAME + " " + CommandOperator.DISPLAY_WALL.getReservedKeyword());
        assertTrue(parsedCommand.getUsername().equals(VALID_USERNAME));
        assertTrue(parsedCommand.getOperator().equals(CommandOperator.DISPLAY_WALL));
        assertTrue(parsedCommand.getParameter().equals(ParsedCommand.NO_PARAMETER));
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidDisplayWallCommand() throws InvalidInputException, EmptyCommandException {
        this.commandParser.parse(VALID_USERNAME + " walle");
    }

}
