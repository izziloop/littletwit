package com.jrochas.littletwit.users;

import java.time.LocalDate;
import java.time.Period;

public class Message {

    // TODO text constraints

    protected static final Message EMPTY = new Message();

    private final String text;

    private final LocalDate timestamp;

    private Message() {
        this.text = "";
        this.timestamp = LocalDate.MIN;
    }

    protected Message(String text, LocalDate timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    protected String getDisplayableMessage() {
        return this.text + " (" + this.computeTimeFromNow();
    }

    private String computeTimeFromNow() {
        LocalDate now = LocalDate.now();
        Period between = Period.between(now, this.timestamp);
        return between.toString();
    }

}
