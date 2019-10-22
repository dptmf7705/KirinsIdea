package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.ui.highlight.Highlight;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface HighlightRepository extends BaseRepository {

    @NonNull
    Completable observeAddNewHighlight(@NonNull final Highlight highlight);

    @NonNull
    Completable observeUpdateHighlight(@NonNull final Highlight highlight);

    @NonNull
    Single<List<Highlight>> observeHighlightListByBookmarkId(final int bookmarkId);

    @NonNull
    Completable observeDeleteHighlight(@NonNull final Highlight highlight);
}
