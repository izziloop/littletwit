package com.jrochas.littletwit.message;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TimelineTest {

    private static final String MESSAGE_TEXT = "a valid message text";

    private static final double NUMBER_OF_MESSAGES = Math.pow(2,15);

    private TimeLine timeLine;

    @Before
    public void setup() {
        this.timeLine = new TimeLine();
    }

    @Test
    public void testEmptyTimelineHasNoMessage() {
        assertTrue(this.timeLine.getAllMessagesCopy().size() == 0);
        assertTrue(this.timeLine.getAllDisplayableMessages().size() == 0);
    }

    @Test
    public void testAddMessageToTimeline() {

        for (int i = 0 ; i < NUMBER_OF_MESSAGES ; i++) {
            this.timeLine.addMessage(MESSAGE_TEXT);
        }

        assertTrue(this.timeLine.getAllMessagesCopy().size() == NUMBER_OF_MESSAGES);
        assertTrue(this.timeLine.getAllDisplayableMessages().size() == NUMBER_OF_MESSAGES);
    }

}
