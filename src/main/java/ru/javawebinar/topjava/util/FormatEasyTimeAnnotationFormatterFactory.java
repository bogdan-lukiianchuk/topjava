package ru.javawebinar.topjava.util;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class FormatEasyTimeAnnotationFormatterFactory implements AnnotationFormatterFactory<EasyTime> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(LocalTime.class);
    }

    @Override
    public Printer<?> getPrinter(EasyTime annotation, Class<?> fieldType) {
        return null;
    }

    @Override
    public Parser<?> getParser(EasyTime annotation, Class<?> fieldType) {
        return new LocalTimeFormatter();
    }
}
