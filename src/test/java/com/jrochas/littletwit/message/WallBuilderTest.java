package com.jrochas.littletwit.message;

import com.jrochas.littletwit.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallBuilderTest {

    private static final String MESSAGE = "a valid message";

    private static final LocalDate NOW_DATE = LocalDate.now();

    private static final LocalTime NOW_TIME = LocalTime.now();

    private static final int MESSAGE_SECONDS_DIFFERENCE = 10;

    @Mock
    private User userMock;

    @InjectMocks
    private WallBuilder wallBuilder;

    @Before
    public void setup() {
        this.wallBuilder = new WallBuilder(this.userMock);

        Map<String, LinkedList<Message>> wallMessages = new HashMap<>();

        wallMessages.put("user1", new LinkedList<>(Arrays.asList(
                new Message(MESSAGE + "3", NOW_DATE, NOW_TIME.minusSeconds(MESSAGE_SECONDS_DIFFERENCE)),
                new Message(MESSAGE + "1", NOW_DATE, NOW_TIME.plusSeconds(MESSAGE_SECONDS_DIFFERENCE)))));

        wallMessages.put("user2", new LinkedList<>(Collections.singletonList(
                new Message(MESSAGE + "2", NOW_DATE, NOW_TIME))));

        when(this.userMock.getAllFollowedMessagesByUsername()).thenReturn(wallMessages);
    }

    @Test
    public void testWallBuilderReturnsAllWallMessagesOrdered() {

        Deque<String> orderedMessages = this.wallBuilder.buildWall();

        int messagePosition = 1;

        for(String message: orderedMessages) {
            assertTrue(message.contains(MESSAGE + messagePosition));
            messagePosition++;
        }
    }

}
