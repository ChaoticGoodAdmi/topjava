package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleUtil {

    private final MessageSource messageSource;

    @Autowired
    public LocaleUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String property) {
        return messageSource.getMessage(property, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String property, Locale locale) {
        return messageSource.getMessage(property, null, locale);
    }
}
