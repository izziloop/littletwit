package com.jrochas.littletwit.users;

import java.time.LocalDate;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TimeLine {

    private List<Message> lastMessages;

    protected TimeLine() {
        this.lastMessages = new LinkedList<>();
    }

    protected void addMessage(String message) {
        this.lastMessages.add(new Message(message, LocalDate.now()));
    }

    protected Deque<String> getAllDisplayableMessages() {
        Deque<String> displayableMessages = new LinkedList<>();
        this.lastMessages.forEach(message -> displayableMessages.push(message.getDisplayableMessage()));
        return displayableMessages;
    }

}
