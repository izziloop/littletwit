package com.jrochas.littletwit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class LittleTwit {

    private static final Logger logger = LogManager.getLogger(LittleTwit.class);

    public static void main(String[] args) {

        Scanner input = new Scanner(Properties.INPUT);

        UserManager userManager = new UserManager();
        CommandParser commandParser = new CommandParser();

        ParsedCommand parsedCommand;

        while (input.hasNext()) {

            try {
                Properties.OUTPUT.print(Properties.PROMPT_CHARACTER);
                parsedCommand = commandParser.parse(input.nextLine());
                userManager.execute(parsedCommand);
            } catch (EmptyCommandException e) {
                // ignore empty commands
            } catch (InvalidInputException e) {
                logger.warn("Main loop encountered an invalid input", e);
            } catch (RuntimeException e) {
                logger.error("Runtime exception while executing main loop", e);
            }

        }
    }

}
