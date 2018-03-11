package com.jrochas.littletwit.message;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {

    private final String text;

    private final LocalDate date;

    private final LocalTime time;

    private final MessageFormatter messageFormatter;

    protected Message(String text, LocalDate date, LocalTime time) {
        this.text = text;
        this.date = date;
        this.time = time;
        this.messageFormatter = new MessageFormatter(this);
    }

    public String getText() {
        return this.text;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    protected boolean isOlderThan(Message message) {
        if (this.date.isBefore(message.date)) {
            return true;
        } else if (this.date.isAfter(message.date)) {
            return false;
        } else if (this.time.isBefore(message.time)) {
            return true;
        } else if (this.time.isAfter(message.time)) {
            return false;
        } else {
            return false;
        }
    }

    protected String getDisplayableMessage() {
        String displayableMessageAging = this.messageFormatter.getDisplayableMessageAging();
        return this.text + " (" + displayableMessageAging + ")";
    }

}
