package com.tw.flyhigh.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    public static Long convertToMilliSec(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static LocalDateTime transferTimeStampToLocalDateTime(Long timeStamp) {
        if (timeStamp == null) {
            return null;
        } else {
            return Instant.ofEpochMilli(timeStamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        }
    }

    public static String format(LocalDateTime entryDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(entryDateTime);
    }

    public static Date formatToDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss");
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
