package com.jrochas.littletwit;

import java.time.LocalDate;
import java.util.Deque;
import java.util.LinkedList;

public class TimeLine {

    private Deque<Message> lastMessages;

    // TODO db

    public TimeLine() {
        this.lastMessages = new LinkedList<>();
    }

    public void addMessage(String message) {
        this.lastMessages.add(new Message(message, LocalDate.now()));
        // TODO if limit is reached, then put in db
    }



}
