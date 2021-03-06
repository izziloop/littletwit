package com.jrochas.littletwit.message;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Deque;
import java.util.LinkedList;

public class TimeLine {

    private LinkedList<Message> messages;

    public TimeLine() {
        this.messages = new LinkedList<>();
    }

    public void addMessage(String text) {
        this.messages.add(new Message(text, LocalDate.now(), LocalTime.now()));
    }

    public LinkedList<Message> getAllMessagesCopy() {
        LinkedList<Message> allMessagesCopy = new LinkedList<>();
        allMessagesCopy.addAll(this.messages);
        return allMessagesCopy;
    }

    public Deque<String> getAllDisplayableMessages() {
        Deque<String> displayableMessages = new LinkedList<>();
        this.messages.forEach(message -> displayableMessages.push(message.getDisplayableMessage()));
        return displayableMessages;
    }

}
