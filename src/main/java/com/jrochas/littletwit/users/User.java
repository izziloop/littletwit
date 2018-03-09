package com.jrochas.littletwit.users;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class User {

    private String username;

    private TimeLine timeLine;

    private Set<User> followedUsers;

    public User(String username) {
        this.username = username;
        this.timeLine = new TimeLine();
        this.followedUsers = new HashSet<>();
    }

    public void addMessage(String parameter) {
        this.timeLine.addMessage(parameter);
    }

    public Deque<String> getAllMessages() {
        return timeLine.getAllDisplayableMessages();
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
