package com.jrochas.littletwit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jrochas.littletwit.CommandParser.CommandToken.OPERATOR;
import static com.jrochas.littletwit.CommandParser.CommandToken.USER;


public class CommandParser {

    //TODO use user + command + if arg -- valid arg pattern



    private static final Pattern tokenPattern = Pattern.compile("\\p{javaWhitespace}");
    private static final Pattern alphanumericPattern = Pattern.compile("[a-zA-Z0-9]*");




    public ParsedCommand parse(String command) throws InvalidInputException, EmptyCommandException {

        String[] commandTokens = tokenPattern.split(command);

        this.validateTokenNumber(commandTokens);

        String username = parseUsername(commandTokens);

        if (commandTokens.length == 1) {
            return new ParsedCommand(username, CommandOperator.VIEW_TIMELINE);
        }

        CommandOperator commandOperator = parseCommandOperator(commandTokens);

        if (commandTokens.length == 2) {
            return new ParsedCommand(username, commandOperator);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            return new ParsedCommand(username, commandOperator, stringBuilder.toString());
        }

    }

    private void validateTokenNumber(String[] commandTokens) throws EmptyCommandException {
        if (commandTokens.length == 0) {
            throw new EmptyCommandException();
        }
    }

    private CommandOperator parseCommandOperator(String[] commandToken) throws InvalidInputException {
        CommandOperator commandOperator = null;

        String parsedCommandOperator = commandToken[OPERATOR.ordinal()];

        for (CommandOperator validOperator: CommandOperator.values()) {
            if (parsedCommandOperator.equals(validOperator.getReservedKeyword())) {
                commandOperator = CommandOperator.valueOf(parsedCommandOperator);
                break;
            }
        }

        if (commandOperator != null) {
            return commandOperator;
        } else {
            throw new InvalidInputException("Command is not recognized");
        }
    }

    private String parseUsername(String[] commandTokens) throws InvalidInputException {

        String parsedUsername = commandTokens[USER.ordinal()];

        Matcher matcher = alphanumericPattern.matcher(parsedUsername);

        if (matcher.matches()) {
            System.out.println("user is compliant");
            return parsedUsername;
        } else {
            throw new InvalidInputException("Username " + parsedUsername + " is not alphanumerical");
        }
    }

    enum CommandToken {

        USER,

        OPERATOR,

        PARAMETER
    }

}
