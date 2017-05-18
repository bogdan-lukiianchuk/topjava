package ru.javawebinar.topjava.util;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class CustomFormatRegistrar implements FormatterRegistrar {
    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new FormatEasyDateAnnotationFormatterFactory());
        registry.addFormatterForFieldAnnotation(new FormatEasyTimeAnnotationFormatterFactory());
    }
}
