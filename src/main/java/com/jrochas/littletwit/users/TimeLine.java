package com.jrochas.littletwit.users;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Deque;
import java.util.LinkedList;

public class TimeLine {

    private LinkedList<Message> messages;

    protected TimeLine() {
        this.messages = new LinkedList<>();
    }

    protected void addMessage(String text) {
        this.messages.add(new Message(text, LocalDate.now(), LocalTime.now()));
    }

    protected LinkedList<Message> getAllMessages() {
        return this.messages;
    }

    protected Deque<String> getAllDisplayableMessages() {
        Deque<String> displayableMessages = new LinkedList<>();
        this.messages.forEach(message -> displayableMessages.push(message.getDisplayableMessage()));
        return displayableMessages;
    }

}
