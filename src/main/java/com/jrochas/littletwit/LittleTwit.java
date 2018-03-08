package com.jrochas.littletwit;

import java.util.Scanner;

public class LittleTwit {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();

        ParsedCommand parsedCommand;

        while (input.hasNext()) {

            try {
                parsedCommand = commandParser.parse(input.nextLine());
                parsedCommand.execute();
            } catch (InvalidInputException e) {
                System.err.println(e);
            } catch (EmptyCommandException e) {
                // ignore empty commands
            }

        }
    }

}
