package com.jrochas.littletwit.users;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TimeLine {

    private LinkedList<Message> messages;

    protected TimeLine() {
        this.messages = new LinkedList<>();
    }

    protected void addMessage(String text) {
        this.messages.add(new Message(text, LocalDate.now(), LocalTime.now()));
    }

    protected LinkedList<Message> getAllMessagesCopy() {
        LinkedList<Message> allMessagesCopy = new LinkedList<>();
        allMessagesCopy.addAll(this.messages);
        return allMessagesCopy;
    }

    protected Deque<String> getAllDisplayableMessages() {
        Deque<String> displayableMessages = new LinkedList<>();
        this.messages.forEach(message -> displayableMessages.push(message.getDisplayableMessage()));
        return displayableMessages;
    }

}
