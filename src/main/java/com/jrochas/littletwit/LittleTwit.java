package com.jrochas.littletwit;

import com.jrochas.littletwit.command.CommandParser;
import com.jrochas.littletwit.command.ParsedCommand;
import com.jrochas.littletwit.exceptions.EmptyCommandException;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import com.jrochas.littletwit.command.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class LittleTwit {

    private static final Logger logger = LogManager.getLogger(LittleTwit.class);

    public static void main(String[] args) {

        Scanner input = new Scanner(Properties.INPUT);

        CommandParser commandParser = new CommandParser();
        CommandExecutor commandExecutor = new CommandExecutor();

        printWelcomeLine();

        printPrompt();

        ParsedCommand parsedCommand;

        while (input.hasNextLine()) {

            try {

                String enteredCommand = input.nextLine();

                logger.info("Received command: " + enteredCommand);

                parsedCommand = commandParser.parse(enteredCommand);
                commandExecutor.execute(parsedCommand);

            } catch (EmptyCommandException e) {
                logger.warn("Main loop encountered an empty input", e);
            } catch (InvalidInputException e) {
                Properties.OUTPUT.println(e.getMessage());
                logger.warn("Main loop encountered an invalid input", e);
            } catch (RuntimeException e) {
                logger.error("Runtime exception while executing main loop", e);

            } finally {

                printPrompt();
            }
        }
    }

    private static void printWelcomeLine() {
        Properties.OUTPUT.println();
        Properties.OUTPUT.println("Welcome to LittleTwit 1.0! Start using LittleTwit by posting messages. Usage:");
        Properties.OUTPUT.println("Posting messages: <username> -> <message>");
        Properties.OUTPUT.println("Reading messages: <username>");
        Properties.OUTPUT.println("Following user: <username> follows <username>");
        Properties.OUTPUT.println("Display wall: <username> wall");
        Properties.OUTPUT.println();
    }

    private static void printPrompt() {
        Properties.OUTPUT.print(Properties.PROMPT_CHARACTER + " ");
    }

}
