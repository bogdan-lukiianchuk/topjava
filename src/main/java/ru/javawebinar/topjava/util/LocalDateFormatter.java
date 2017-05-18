package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        try {
            String[] data = text.split("\\.", 3);
            return LocalDate.of(
                    Integer.valueOf(data[0]),
                    Integer.valueOf(data[1]),
                    Integer.valueOf(data[2])
            );
        } catch (Exception e) {
            ParseException parseException = new ParseException("", 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return null;
    }
}
