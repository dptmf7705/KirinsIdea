package com.kirinsidea.utils;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;

import java.util.Date;

public class DataUtil {
    @NonNull
    public static String getCurrentDateTime() {
        return DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date())
                .toString();
    }
}
