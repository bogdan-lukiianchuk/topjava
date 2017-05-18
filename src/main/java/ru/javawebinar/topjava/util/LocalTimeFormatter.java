package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        try {
            String[] data = text.split(":", 2);
            return LocalTime.of(
                    Integer.valueOf(data[0]),
                    Integer.valueOf(data[1])
            );
        } catch (Exception e) {
            ParseException parseException = new ParseException("", 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return null;
    }
}
