package com.jrochas.littletwit.users;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class MessageFormatter {

    private final Message message;

    public MessageFormatter(Message message) {
        this.message = message;
    }

    protected String getDisplayableMessageAging() {
        StringBuilder displayableMessageAging = new StringBuilder();

        LocalDate today = LocalDate.now();
        Period periodBetween = Period.between(this.message.getDate(), today);

        if (periodBetween.getDays() > 0) {
            this.appendDisplayablePeriod(displayableMessageAging, periodBetween);
        } else {
            LocalTime now = LocalTime.now();
            this.appendDisplayableDuration(displayableMessageAging, now);
        }

        displayableMessageAging.append(" ago");

        return displayableMessageAging.toString();
    }

    private void appendDisplayableDuration(StringBuilder builtString, LocalTime now) {
        Duration between = Duration.between(this.message.getTime(), now);

        long hours = between.toHours();
        if (hours > 0) {
            builtString.append(hours).append(" hour");
            this.appendPluralIfNeeded(builtString, hours);
        }

        long minutes = between.toMinutes();
        if (minutes > 0) {
            if (hours > 0) builtString.append(" ");
            builtString.append(minutes).append(" minute");
            this.appendPluralIfNeeded(builtString, minutes);
        } else {
            long seconds = between.getSeconds();
            builtString.append(seconds).append(" second");
            this.appendPluralIfNeeded(builtString, minutes);
        }
    }

    private void appendDisplayablePeriod(StringBuilder builtString, Period periodBetween) {
        int years = periodBetween.getYears();
        if (years > 0) {
            builtString.append(years).append(" year");
            this.appendPluralIfNeeded(builtString, years);
        }

        int months = periodBetween.getMonths();
        if (months > 0) {
            if (years > 0) builtString.append(" ");
            builtString.append(months).append(" month");
            this.appendPluralIfNeeded(builtString, months);
        } else {
            int days = periodBetween.getDays();
            builtString.append(days).append(" day");
            this.appendPluralIfNeeded(builtString, days);
        }
    }

    private void appendPluralIfNeeded(StringBuilder builtString, long metric) {
        if (metric > 1) {
            builtString.append("s");
        }
    }

}
