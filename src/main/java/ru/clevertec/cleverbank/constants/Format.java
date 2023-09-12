package ru.clevertec.cleverbank.constants;

import java.time.format.DateTimeFormatter;

public class Format {
    public static final String PATTERN_DATE_TIME_LINE = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DATE_TIME_POINT = "dd.MM.yyyy hh:MM:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME_LINE);
}
