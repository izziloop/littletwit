package com.jrochas.littletwit;

import java.time.LocalDate;

public class Message {

    // TODO text constraints

    private final String text;

    private final LocalDate timestamp;

    public Message(String text, LocalDate timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

}
