package com.jrochas.littletwit.message;

import com.jrochas.littletwit.user.User;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class WallBuilder {

    private final User user;

    public WallBuilder(User user) {
        this.user = user;
    }

    public Deque<String> buildWall() {

        Deque<String> wall = new LinkedList<>();

        Map<String, LinkedList<Message>> allFollowedMessagesByUsername = this.user.getAllFollowedMessagesByUsername();

        String selectedUsername;
        Message selectedMessage;

        while (!allFollowedMessagesByUsername.isEmpty()) {

            selectedUsername = null;
            selectedMessage = null;

            for (Map.Entry<String, LinkedList<Message>> userEntry: allFollowedMessagesByUsername.entrySet()) {
                LinkedList<Message> currentUserMessages = userEntry.getValue();
                if (selectedMessage == null || currentUserMessages.getFirst().isOlderThan(selectedMessage)) {
                    selectedMessage = currentUserMessages.getFirst();
                    selectedUsername = userEntry.getKey();
                }
            }

            this.removeOldestMessage(allFollowedMessagesByUsername, selectedUsername);

            if (selectedMessage != null) {
                wall.push(selectedUsername + " - " + selectedMessage.getDisplayableMessage());
            }
        }

        return wall;
    }

    private void removeOldestMessage(Map<String, LinkedList<Message>> allFollowedMessagesByUsername, String selectedUsername) {
        LinkedList<Message> selectedUserMessages = allFollowedMessagesByUsername.get(selectedUsername);

        if (!selectedUserMessages.isEmpty()) {
            selectedUserMessages.removeFirst();
        }

        if (selectedUserMessages.isEmpty()) {
            allFollowedMessagesByUsername.remove(selectedUsername);
        }
    }
}
