package com.udacity.android.famousmovies.data.database;

import android.arch.persistence.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @TypeConverter
    public static Date toDate(String date) {
        try {
            return date == null ? null : DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @TypeConverter
    public static String toString(Date date) {
        return date == null ? null : DATE_FORMAT.format(date);
    }
}
