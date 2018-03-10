package com.jrochas.littletwit.command;

public class ParsedCommand {

    private String username;

    private CommandOperator operator;


    private String parameter;

    protected ParsedCommand(String username, CommandOperator operator) {
        this(username, operator, "");
    }

    protected ParsedCommand(String username, CommandOperator operator, String parameter) {
        this.username = username;
        this.operator = operator;
        this.parameter = parameter;
    }


    public CommandOperator getOperator() {
        return this.operator;
    }

    public String getUsername() {
        return this.username;
    }

    public String getParameter() {
        return this.parameter;
    }
}
