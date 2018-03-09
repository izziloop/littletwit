package com.jrochas.littletwit;

import com.jrochas.littletwit.users.User;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class UserManager {

    private Map<String, User> users;

    public UserManager() {
        this.init();
    }

    private void init() {
        // TODO load from database
    }

    public void execute(ParsedCommand parsedCommand) {

        String username = parsedCommand.getUsername();

        CommandOperator commandOperator = parsedCommand.getOperator();

        String commandParameter = parsedCommand.getParameter();

        switch (commandOperator) {
            case POST_MESSAGE:
                this.addMessageToUser(commandParameter, username);
                break;
            case VIEW_TIMELINE:
                this.seeAllMessagesOfUser(username);
                break;
            case FOLLOW_USER:
                this.addFollowedUserToUser();
                break;
            case DISPLAY_WALL:
                this.displayWall();
                break;
            default:
                throw new IllegalArgumentException("Command operator " + commandOperator + " does not exist");
        }
    }

    private void addMessageToUser(String message, String username) {

        User user = this.users.get(username);

        this.addUserIfNotExist(username, user);

        user.addMessage(message);
    }

    private void addUserIfNotExist(String username, User user) {
        if (user == null) {
            user = new User(username);
            this.users.put(username, user);
        }
    }

    private void seeAllMessagesOfUser(String username) {
        this.users.get(username).getAllMessages().forEach(Properties.OUTPUT::println);
    }

    private void addFollowedUserToUser() {
    }

    private void displayWall() {
    }

}
