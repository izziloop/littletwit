package com.jrochas.littletwit;

public class ParsedCommand {

    private String username;

    private CommandOperator operator;

    private String parameter;

    public ParsedCommand(String username, CommandOperator operator) {
        this.username = username;
        this.operator = operator;
    }

    public ParsedCommand(String username, CommandOperator operator, String parameter) {
        this(username, operator);
        this.parameter = parameter;
    }

    public void execute() {

    }

}
