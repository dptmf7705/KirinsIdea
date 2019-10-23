package com.kirinsidea.utils;

import androidx.annotation.Nullable;

import java.util.Collection;

public class CollectionUtil {

    public static boolean isEmpty(@Nullable final Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
