package com.kirinsidea.data.repository.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.ui.highlight.Highlight;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface HighlightRepository extends BaseRepository {

    @NonNull
    Single<Highlight> observeAddNewHighlight(@NonNull final Highlight highlight);

    @NonNull
    Single<Highlight> observeUpdateHighlight(@NonNull final Highlight highlight);

    @NonNull
    Single<List<Highlight>> observeHighlightListByBookmarkId(final int bookmarkId);

    @NonNull
    Completable observeDeleteHighlight(@NonNull final Highlight highlight);

    // TODO 서버 완료될 때까지 임시
    @NonNull
    Single<Highlight> observeHighlight(final int bookmarkId, final int start, final int end);
}
