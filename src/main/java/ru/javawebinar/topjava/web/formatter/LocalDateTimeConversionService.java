package ru.javawebinar.topjava.web.formatter;

import org.springframework.context.annotation.Bean;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LocalDateTimeConversionService {

    private LocalDateFormatter localDateFormatter() {
        return new LocalDateFormatter();
    }

    private LocalTimeFormatter localTimeFormatter() {
        return new LocalTimeFormatter();
    }

    @Bean
    public FormattingConversionServiceFactoryBean getConversionService() {
        FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
        conversionService.setFormatters(Set.of(localDateFormatter(), localTimeFormatter()));
        return conversionService;
    }
}
