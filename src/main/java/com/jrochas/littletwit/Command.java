package com.jrochas.littletwit;

public enum Command {

    POST_MESSAGE("->", true),

    VIEW_TIMELINE("", false),

    FOLLOW_USER("follows", true),

    DISPLAY_WALL("wall", false);

    //TODO use user + command + if arg -- valid arg pattern
    private static final String validInputPattern = "";

    private String keyword;

    private boolean hasArguments;

    private Command(String keyword, boolean hasArguments) {
        this.keyword = keyword;
        this.hasArguments = hasArguments;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public boolean hasArguments() {
        return this.hasArguments;
    }

}
