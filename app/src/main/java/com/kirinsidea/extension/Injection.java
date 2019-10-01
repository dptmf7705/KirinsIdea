package com.kirinsidea.extension;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;

public class Injection {

    public static BookmarkViewModel provideBookmarkViewModel(
            @NonNull final FragmentActivity activity) {

        return ViewModelProviders
                .of(activity, new BookmarkViewModel.Factory(
                        provideBookmarkRepository(activity)))
                .get(BookmarkViewModel.class);
    }

    public static BookmarkRepository provideBookmarkRepository(
            @NonNull final Context context) {

        return BookmarkRepositoryImpl.getInstance(
                provideBookmarkDao(context));
    }

    public static BookmarkDao provideBookmarkDao(
            @NonNull final Context context) {

        return provideRoomDatabase(context).bookmarkDao();
    }

    public static AppDatabase provideRoomDatabase(
            @NonNull final Context context) {

        return AppDatabase.getDatabase(context);
    }
}
