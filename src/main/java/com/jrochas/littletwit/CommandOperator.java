package com.jrochas.littletwit;

public enum CommandOperator {

    POST_MESSAGE("->", true),

    VIEW_TIMELINE("", false),

    FOLLOW_USER("follows", true),

    DISPLAY_WALL("wall", false);

    private String keyword;

    private boolean hasArguments;

    private CommandOperator(String keyword, boolean hasArguments) {
        this.keyword = keyword;
        this.hasArguments = hasArguments;
    }

    public String getReservedKeyword() {
        return this.keyword;
    }

    public boolean hasArguments() {
        return this.hasArguments;
    }

}
