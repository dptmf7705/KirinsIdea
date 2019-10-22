package com.kirinsidea.utils;

import androidx.annotation.Nullable;

public class ArrayUtil {

    public static <T> boolean isEmpty(@Nullable final T[] array) {
        return array == null || array.length == 0;
    }
}
