package com.kirinsidea.extension.livedata;

import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class LiveDataCompat {

    public static <T> T getValue(@NonNull final LiveData<T> liveData,
                                 @NonNull final T defaultValue) {

        return liveData.getValue() == null ? defaultValue : liveData.getValue();
    }

    public static <T> List<T> getListValue(@NonNull final MutableLiveData<List<T>> liveData) {

        if (liveData.getValue() == null) {
            liveData.setValue(new ArrayList<>());
        }
        return liveData.getValue();
    }

    public static <F, S> F getFirstValue(@NonNull final LiveData<Pair<F, S>> liveData,
                                         @NonNull final F defaultValue) {

        return liveData.getValue() == null ? defaultValue : liveData.getValue().first;
    }

    public static <F, S> S getSecondValue(@NonNull final LiveData<Pair<F, S>> liveData,
                                          @NonNull final S defaultValue) {

        return liveData.getValue() == null ? defaultValue : liveData.getValue().second;
    }

    public static <T extends CharSequence> boolean isEmpty(@NonNull final LiveData<T> liveData) {

        return liveData.getValue() == null || TextUtils.isEmpty(liveData.getValue());
    }

    public static <T> void notifyDataChange(@NonNull final MutableLiveData<T> liveData) {
        liveData.setValue(liveData.getValue());
    }
}
