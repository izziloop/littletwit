package com.jrochas.littletwit.user;

import com.jrochas.littletwit.message.Message;
import com.jrochas.littletwit.message.TimeLine;

import java.util.*;

public class User {

    private String username;

    private TimeLine timeLine;

    private Set<User> followedUsers;

    public User(String username) {
        this.username = username;
        this.timeLine = new TimeLine();
        this.followedUsers = new HashSet<>();
    }

    public String getUsername() {
        return this.username;
    }

    public void addMessage(String parameter) {
        this.timeLine.addMessage(parameter);
    }

    public Deque<String> getAllMessages() {
        return this.timeLine.getAllDisplayableMessages();
    }

    public void addFollowedUser(User followedUser) {
        this.followedUsers.add(followedUser);
    }

    public Map<String, LinkedList<Message>> getAllFollowedMessagesByUsername() {

        Map<String, LinkedList<Message>> allFollowedMessagesByUsername = new HashMap<>();

        allFollowedMessagesByUsername.put(this.username, this.timeLine.getAllMessagesCopy());
        this.followedUsers.forEach(followedUser -> allFollowedMessagesByUsername.put(followedUser.username, followedUser.timeLine.getAllMessagesCopy()));

        return allFollowedMessagesByUsername;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof User) {
            return ((User) other).username.equals(this.username);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

}
