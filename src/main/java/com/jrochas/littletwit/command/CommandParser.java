package com.jrochas.littletwit.command;


import com.jrochas.littletwit.exceptions.EmptyCommandException;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandParser {

    private static final Logger logger = LogManager.getLogger(CommandParser.class);

    private static final String WHITE_SPACE_REGEX = "\\p{javaWhitespace}";
    private static final String ALL_WHITE_SPACE_REGEX = WHITE_SPACE_REGEX + "+";

    private static final Pattern TOKEN_PATTERN = Pattern.compile(ALL_WHITE_SPACE_REGEX);
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    public ParsedCommand parse(String command) throws InvalidInputException, EmptyCommandException {

        String[] commandTokens = TOKEN_PATTERN.split(command.trim());

        this.validateTokenNumber(commandTokens);

        String username = this.parseUsername(commandTokens);

        if (commandTokens.length == 1) {
            return new ParsedCommand(username, CommandOperator.VIEW_TIMELINE);
        }

        CommandOperator commandOperator = this.parseCommandOperator(commandTokens);

        if (commandTokens.length == 2) {
            return new ParsedCommand(username, commandOperator);
        } else {

            String commandParameter = this.parseCommandParameter(command, username, commandOperator);
            return new ParsedCommand(username, commandOperator, commandParameter);
        }

    }

    private String parseCommandParameter(String command, String username, CommandOperator commandOperator) {

        Pattern commandOperatorPattern = Pattern.compile(commandOperator.getReservedKeyword(), Pattern.LITERAL);

        String commandParameter = this.removeUserAndOperatorFromCommand(command, username, commandOperatorPattern);

        if (logger.isDebugEnabled()) {
            logger.debug("Parsed command operator: " + commandOperator);
        }

        return commandParameter;
    }

    private String removeUserAndOperatorFromCommand(String command, String username, Pattern commandOperatorPattern) {

        return Pattern.compile(username + ALL_WHITE_SPACE_REGEX + commandOperatorPattern.pattern() + ALL_WHITE_SPACE_REGEX)
                .matcher(command).replaceFirst("").trim();
    }

    private void validateTokenNumber(String[] commandTokens) throws EmptyCommandException {
        if (logger.isDebugEnabled()) {
            logger.debug("Tokenized command: " + Arrays.toString(commandTokens));
        }

        if (commandTokens.length == 0) {
            throw new EmptyCommandException();
        }
    }

    private CommandOperator parseCommandOperator(String[] commandToken) throws InvalidInputException {
        CommandOperator commandOperator = null;

        String commandOperatorToken = commandToken[CommandToken.OPERATOR.ordinal()];

        for (CommandOperator validOperator : CommandOperator.values()) {
            if (commandOperatorToken.equals(validOperator.getReservedKeyword())) {
                commandOperator = CommandOperator.getEnum(commandOperatorToken);
                break;
            }
        }

        if (commandOperator != null) {

            logger.debug("Parsed command operator: " + commandOperator);

            return commandOperator;
        } else {
            throw new InvalidInputException("Command " + commandOperatorToken + " is not recognized");
        }
    }

    private String parseUsername(String[] commandTokens) throws InvalidInputException {

        String usernameToken = commandTokens[CommandToken.USER.ordinal()];

        Matcher matcher = ALPHANUMERIC_PATTERN.matcher(usernameToken);

        if (matcher.matches()) {

            logger.debug("Parsed username: " + usernameToken);

            return usernameToken;
        } else {
            throw new InvalidInputException("Username " + usernameToken + " is not alphanumerical");
        }
    }

    private enum CommandToken {

        USER,

        OPERATOR,

    }

}
