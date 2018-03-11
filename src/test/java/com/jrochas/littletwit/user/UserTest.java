package com.jrochas.littletwit.user;

import com.jrochas.littletwit.message.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    private static final String TEST_USERNAME = "John";

    private static final String MESSAGE = "a valid message";

    private static final int NUMBER_OF_MESSAGES = 15;

    private User testUser;

    @Before
    public void setup() {
        this.testUser = new User(TEST_USERNAME);
    }

    @Test
    public void testNullUserIsNotEqual() {
        User otherUser = null;
        assertFalse(this.testUser.equals(otherUser));
    }

    @Test
    public void testAnyOtherObjectIsNotEqual() {
        String otherUserName = "Doe";
        assertFalse(this.testUser.equals(otherUserName));
    }

    @Test
    public void testOtherUserWithDifferentNameIsNotEqual() {
        User otherUser = new User("Doe");
        assertFalse(this.testUser.equals(otherUser));
    }

    @Test
    public void testOtherUserWithSameNameEqual() {
        User otherUser = new User(TEST_USERNAME);
        assertTrue(this.testUser.equals(otherUser));
    }

    @Test
    public void testEqualUsersHaveSameHashCode() {
        User otherUser = new User(TEST_USERNAME);
        assertEquals(this.testUser.hashCode(), otherUser.hashCode());
    }

    @Test
    public void testAddedMessageCanBeRetrieved() {
        for (int i = 0 ; i < NUMBER_OF_MESSAGES ; i++) {
            this.testUser.addMessage(MESSAGE);
        }

        assertTrue(this.testUser.getAllMessages().size() == NUMBER_OF_MESSAGES);
        this.testUser.getAllMessages().forEach(message -> assertTrue(message.contains(MESSAGE)));
    }

    @Test
    public void testAddedFollowedUserMessageCanBeRetrieved() {
        User otherUser = new User("Doe");
        otherUser.addMessage(MESSAGE);

        this.testUser.addMessage(MESSAGE);

        this.testUser.addFollowedUser(otherUser);

        Map<String, LinkedList<Message>> allFollowedMessagesByUsername = this.testUser.getAllFollowedMessagesByUsername();

        assertTrue(allFollowedMessagesByUsername.size() == 2);

        LinkedList<String> usersPresentInFollowedMessage = new LinkedList<>();

        allFollowedMessagesByUsername.forEach((user, messages) -> {
            usersPresentInFollowedMessage.add(user);
            assertTrue(messages.size() == 1);
            assertTrue(messages.getFirst().getText().equals(MESSAGE));
        });

        assertTrue(usersPresentInFollowedMessage.contains(this.testUser.getUsername()));
        assertTrue(usersPresentInFollowedMessage.contains(otherUser.getUsername()));
    }

}
