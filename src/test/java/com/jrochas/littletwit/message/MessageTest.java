package com.jrochas.littletwit.message;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageTest {

    private static final String MESSAGE_TEXT = "a valid message text";

    private static final LocalDate NOW_DATE = LocalDate.now();

    private static final LocalTime NOW_TIME = LocalTime.now();

    private static final int DIFFERENCE = 2;

    private Message nowMessage;

    private Message olderMessageYears;

    private Message olderMessageMonths;

    private Message olderMessageDays;

    private Message olderMessageHours;

    private Message olderMessageMinutes;

    private Message olderMessageSeconds;

    private Message laterMessageYears;

    private Message laterMessageMonths;

    private Message laterMessageDays;

    private Message laterMessageHours;

    private Message laterMessageMinutes;

    private Message laterMessageSeconds;

    @Before
    public void setup() {
        this.nowMessage = new Message(MESSAGE_TEXT, NOW_DATE, NOW_TIME);

        this.olderMessageYears = new Message(MESSAGE_TEXT, this.nowMessage.getDate().minusYears(DIFFERENCE), NOW_TIME);
        this.olderMessageMonths = new Message(MESSAGE_TEXT, this.nowMessage.getDate().minusMonths(DIFFERENCE), NOW_TIME);
        this.olderMessageDays = new Message(MESSAGE_TEXT, this.nowMessage.getDate().minusDays(DIFFERENCE), NOW_TIME);
        this.olderMessageHours = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().minusHours(DIFFERENCE));
        this.olderMessageMinutes = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().minusMinutes(DIFFERENCE));
        this.olderMessageSeconds = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().minusSeconds(DIFFERENCE));

        this.laterMessageYears = new Message(MESSAGE_TEXT, this.nowMessage.getDate().plusYears(DIFFERENCE), NOW_TIME);
        this.laterMessageMonths = new Message(MESSAGE_TEXT, this.nowMessage.getDate().plusMonths(DIFFERENCE), NOW_TIME);
        this.laterMessageDays = new Message(MESSAGE_TEXT, this.nowMessage.getDate().plusDays(DIFFERENCE), NOW_TIME);
        this.laterMessageHours = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().plusHours(DIFFERENCE));
        this.laterMessageMinutes = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().plusMinutes(DIFFERENCE));
        this.laterMessageSeconds = new Message(MESSAGE_TEXT, NOW_DATE, this.nowMessage.getTime().plusSeconds(DIFFERENCE));
    }

    @Test
    public void testNowMessageIsOlderThanLaterMessage() {
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageYears));
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageMonths));
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageDays));
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageHours));
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageMinutes));
        assertTrue(this.nowMessage.isOlderThan(this.laterMessageSeconds));
    }

    @Test
    public void testNowMessageIsLaterThanOlderMessage() {
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageYears));
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageMonths));
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageDays));
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageHours));
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageMinutes));
        assertFalse(this.nowMessage.isOlderThan(this.olderMessageSeconds));
    }

    @Test
    public void testOlderMessageIsOlderThanLaterMessage() {
        assertTrue(this.olderMessageYears.isOlderThan(this.laterMessageYears));
        assertTrue(this.olderMessageMonths.isOlderThan(this.laterMessageMonths));
        assertTrue(this.olderMessageDays.isOlderThan(this.laterMessageDays));
        assertTrue(this.olderMessageHours.isOlderThan(this.laterMessageHours));
        assertTrue(this.olderMessageMinutes.isOlderThan(this.laterMessageMinutes));
        assertTrue(this.olderMessageSeconds.isOlderThan(this.laterMessageSeconds));
    }

}
