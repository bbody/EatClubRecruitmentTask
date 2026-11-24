package com.brendonbody.util;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimeParser {
    public static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("h:mma")
                    .toFormatter();

    public static LocalTime parse(String source) {
        return LocalTime.parse(source, FORMATTER);
    }
}
