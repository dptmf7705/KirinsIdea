package com.kirinsidea.data.source.local.room;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    /*TODO 정렬 방식 논의 필요*/

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromString(String value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static String fromDate(Date date){
        return date == null ? null : date.toString();
    }
}
