package com.jrochas.littletwit.command;


import com.jrochas.littletwit.exceptions.EmptyCommandException;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jrochas.littletwit.command.CommandParser.CommandToken.OPERATOR;
import static com.jrochas.littletwit.command.CommandParser.CommandToken.USER;


public class CommandParser {

    private static final Logger logger = LogManager.getLogger(CommandParser.class);

    private static final String WHITE_SPACE_REGEX = "\\p{javaWhitespace}";
    private static final String ALL_WHITE_SPACE_REGEX = WHITE_SPACE_REGEX + "+";

    private static final Pattern TOKEN_PATTERN = Pattern.compile(ALL_WHITE_SPACE_REGEX);
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[a-zA-Z0-9]*");

    public ParsedCommand parse(String command) throws InvalidInputException, EmptyCommandException {

        logger.info("Read command: " + command);

        String[] commandTokens = TOKEN_PATTERN.split(command);

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

        return Pattern.compile(username + ALL_WHITE_SPACE_REGEX + commandOperatorPattern.pattern() + ALL_WHITE_SPACE_REGEX)
                .matcher(command).replaceFirst("");
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

        String commandOperatorToken = commandToken[OPERATOR.ordinal()];

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

        String usernameToken = commandTokens[USER.ordinal()];

        Matcher matcher = ALPHANUMERIC_PATTERN.matcher(usernameToken);

        if (matcher.matches()) {

            logger.debug("Parsed username: " + usernameToken);

            return usernameToken;
        } else {
            throw new InvalidInputException("Username " + usernameToken + " is not alphanumerical");
        }
    }

    enum CommandToken {

        USER,

        OPERATOR,

        PARAMETER
    }

}