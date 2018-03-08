package com.jrochas.littletwit;

import java.util.Map;

public class UserManager {

    private Map<String, TimeLine> users;

    public UserManager() {
        this.init();
    }

    private void init() {
        // TODO load from database
    }

    public void addUser(String username) {
        this.users.put(username, new TimeLine());
    }

    public void addMessageToUser(String username, String message) {

    }

}
