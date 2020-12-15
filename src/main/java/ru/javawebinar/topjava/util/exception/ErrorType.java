package ru.javawebinar.topjava.util.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ErrorType {
    @JsonProperty("APP_ERROR")
    APP_ERROR,
    @JsonProperty("DATA_NOT_FOUND")
    DATA_NOT_FOUND,
    @JsonProperty("DATA_ERROR")
    DATA_ERROR,
    @JsonProperty("VALIDATION_ERROR")
    VALIDATION_ERROR
}
