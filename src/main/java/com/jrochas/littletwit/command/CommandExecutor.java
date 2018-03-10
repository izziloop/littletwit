package com.jrochas.littletwit.command;

import com.jrochas.littletwit.Properties;
import com.jrochas.littletwit.exceptions.InvalidInputException;
import com.jrochas.littletwit.users.User;
import com.jrochas.littletwit.users.WallBuilder;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private Map<String, User> users;

    private CommandContentValidator commandContentValidator;

    public CommandExecutor() {
        this.users = new HashMap<>();
        this.commandContentValidator = new CommandContentValidator();
    }

    public void execute(ParsedCommand parsedCommand) throws InvalidInputException {

        String username = parsedCommand.getUsername();

        CommandOperator commandOperator = parsedCommand.getOperator();

        String commandParameter = parsedCommand.getParameter();

        switch (commandOperator) {
            case POST_MESSAGE:
                this.addMessageToUser(username, commandParameter);
                break;
            case VIEW_TIMELINE:
                this.seeAllMessagesOfUser(username);
                break;
            case FOLLOW_USER:
                this.addFollowedUserToUser(username, commandParameter);
                break;
            case DISPLAY_WALL:
                this.displayWall(username);
                break;
            default:
                throw new IllegalArgumentException("Command operator " + commandOperator + " does not exist");
        }
    }

    private void addMessageToUser(String username, String message) throws InvalidInputException {

        this.commandContentValidator.checkMessageSize(message);

        User user = this.getUserOrCreate(username);

        user.addMessage(message);
    }

    private void seeAllMessagesOfUser(String username) throws InvalidInputException {
        User user = this.users.get(username);

        this.commandContentValidator.checkUserNotNull(user, username);

        user.getAllMessages().forEach(Properties.OUTPUT::println);

    }

    private void addFollowedUserToUser(String username, String followedUsername) throws InvalidInputException {

        User followedUser = this.users.get(followedUsername);

        this.commandContentValidator.checkUserNotNull(followedUser, followedUsername);

        User user = this.getUserOrCreate(username);

        user.addFollowedUser(followedUser);
    }

    private User getUserOrCreate(String username) {

        User user = this.users.get(username);

        if (user == null) {
            user = new User(username);
            this.users.put(username, user);
        }

        return user;
    }

    private void displayWall(String username) throws InvalidInputException {

        User user = this.users.get(username);

        this.commandContentValidator.checkUserNotNull(user, username);

        WallBuilder wallBuilder = new WallBuilder(user);

        Deque<String> wallMessages = wallBuilder.buildWall();

        wallMessages.forEach(Properties.OUTPUT::println);

    }

}
