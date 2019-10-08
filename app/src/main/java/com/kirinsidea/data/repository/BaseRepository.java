package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

public interface BaseRepository {

    @NonNull
    BaseRepository init(@NonNull final Object... dataSources);
}
