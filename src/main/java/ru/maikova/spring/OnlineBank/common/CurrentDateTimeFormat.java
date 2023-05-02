package ru.maikova.spring.OnlineBank.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTimeFormat {
    private static DateTimeFormatter formatter;
    private static LocalDateTime dateTime;
    public static String currentDate;

    static {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.now();
        currentDate = dateTime.format(formatter);
    }

    public CurrentDateTimeFormat() {
    }
}
