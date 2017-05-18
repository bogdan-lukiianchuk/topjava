package ru.javawebinar.topjava.util;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class FormatEasyDateAnnotationFormatterFactory
 implements AnnotationFormatterFactory<EasyDate> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(LocalDate.class);
    }

    @Override
    public Printer<?> getPrinter(EasyDate annotation, Class<?> fieldType) {
        return null;
    }

    @Override
    public Parser<?> getParser(EasyDate annotation, Class<?> fieldType) {
        return new LocalDateFormatter();
    }
}
