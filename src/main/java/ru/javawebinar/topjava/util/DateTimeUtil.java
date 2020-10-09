package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetween(T time, T startTime, T endTime, boolean isOpenSecondBoundary) {
        return time.compareTo(startTime) >= 0 &&
                (time.compareTo(endTime) <= 0 && isOpenSecondBoundary || time.compareTo(endTime) < 0 && !isOpenSecondBoundary);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

