package com.jrochas.littletwit;

import java.util.Arrays;

public enum CommandOperator {

    POST_MESSAGE("->", true),

    VIEW_TIMELINE("", false),

    FOLLOW_USER("follows", true),

    DISPLAY_WALL("wall", false);

    private String commandKeyword;

    private boolean hasArguments;

    private CommandOperator(String commandKeyword, boolean hasArguments) {
        this.commandKeyword = commandKeyword;
        this.hasArguments = hasArguments;
    }

    public String getReservedKeyword() {
        return this.commandKeyword;
    }

    public boolean hasArguments() {
        return this.hasArguments;
    }

    public static CommandOperator getEnum(String commandKeyword) {
        return Arrays.stream(CommandOperator.values())
                .filter(commandOperator -> commandOperator.commandKeyword.equals(commandKeyword))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}
