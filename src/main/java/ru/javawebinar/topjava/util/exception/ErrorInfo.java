package ru.javawebinar.topjava.util.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.details = details;
    }

    @JsonCreator
    public ErrorInfo(@JsonProperty("url") String url, @JsonProperty("type") ErrorType type, @JsonProperty("details") String[] details) {
        this.url = url;
        this.type = type;
        this.details = details;
    }

    public ErrorType getType() {
        return type;
    }

    public String[] getDetails() {
        return details;
    }
}