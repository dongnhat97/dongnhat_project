package com.java.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    /**
     * function convert string to local date time
     *
     * @param inputString
     * @param pattern
     * @return LocalDateTime
     */
    public static LocalDateTime convertStringToLocalDateTime(String inputString, String pattern) {
        if (inputString == null || pattern == null) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(inputString, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * function convert string to local date
     *
     * @param inputString
     * @param pattern
     * @return LocalDate
     */
    public static LocalDate convertStringToLocalDate(String inputString, String pattern) {
        if (inputString == null || pattern == null) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(inputString, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * function convert local date time to string
     *
     * @param localDateTime
     * @param pattern
     * @return String
     */
    public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null || pattern == null) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDateTime.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * function convert local date to string
     *
     * @param localDate
     * @param pattern
     * @return String
     */
    public static String convertLocalDateToString(LocalDate localDate, String pattern) {
        if (localDate == null || pattern == null) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }
}
