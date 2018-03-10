package com.jrochas.littletwit.command;

import com.jrochas.littletwit.exceptions.InvalidInputException;
import com.jrochas.littletwit.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandExecutorTest {

    @Mock
    private Map<String, User> userMapMock;

    @Mock
    private CommandContentValidator commandContentValidatorMock;

    @InjectMocks
    private CommandExecutor commandExecutor;

    @Mock
    private ParsedCommand parsedCommandMock;

    @Mock
    private User userMock;

    @Test
    public void testPostMessageChecksMessageSize() throws InvalidInputException {

        when(this.parsedCommandMock.getOperator()).thenReturn(CommandOperator.POST_MESSAGE);

        this.commandExecutor.execute(this.parsedCommandMock);

        verify(this.commandContentValidatorMock, times(1)).checkMessageSize(any());
    }

    @Test
    public void testViewTimelineChecksUserNotNull() throws InvalidInputException {

        when(this.parsedCommandMock.getOperator()).thenReturn(CommandOperator.VIEW_TIMELINE);

        this.executeCommandAndCheckThatCheckUserNotNullIsInvoked();
    }

    @Test
    public void testFollowUserChecksUserNotNull() throws InvalidInputException {

        when(this.parsedCommandMock.getOperator()).thenReturn(CommandOperator.FOLLOW_USER);

        this.executeCommandAndCheckThatCheckUserNotNullIsInvoked();
    }

    @Test
    public void testDisplayWallChecksUserNotNull() throws InvalidInputException {

        when(this.parsedCommandMock.getOperator()).thenReturn(CommandOperator.DISPLAY_WALL);

        this.executeCommandAndCheckThatCheckUserNotNullIsInvoked();
    }

    private void executeCommandAndCheckThatCheckUserNotNullIsInvoked() throws InvalidInputException {
        when(this.parsedCommandMock.getUsername()).thenReturn("username");
        when(this.userMapMock.get(anyString())).thenReturn(this.userMock);
        when(this.userMock.getAllMessages()).thenReturn(new LinkedList<>());

        this.commandExecutor.execute(this.parsedCommandMock);

        verify(this.commandContentValidatorMock, times(1)).checkUserNotNull(any(), any());
    }

}
